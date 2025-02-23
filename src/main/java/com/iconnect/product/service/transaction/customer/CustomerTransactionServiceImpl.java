package com.iconnect.product.service.transaction.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iconnect.product.dto.integration.IntCompanyCustomerProductMappingOutput;
import com.iconnect.product.dto.product.site.product.SiteProductDetailOutput;
import com.iconnect.product.dto.product.site.template.SiteProductTemplateOutput;
import com.iconnect.product.dto.transaction.CustomerTransactionDataInput;
import com.iconnect.product.dto.transaction.CustomerTransactionDataOutput;
import com.iconnect.product.entity.integration.CompanyCustomer;
import com.iconnect.product.entity.integration.CustomerSiteProduct;
import com.iconnect.product.entity.product.SiteBaseProductSettingData;
import com.iconnect.product.entity.transaction.customer.CustomerTransaction;
import com.iconnect.product.entity.transaction.customer.CustomerTransactionMapping;
import com.iconnect.product.repository.integration.CustomerSiteProductRepository;
import com.iconnect.product.repository.product.SiteBaseProductSettingDataRepository;
import com.iconnect.product.repository.transaction.customer.CustomerTransactionMappingRepository;
import com.iconnect.product.repository.transaction.customer.CustomerTransactionRepository;
import com.iconnect.product.service.integration.validator.IntegrationUtil;
import com.iconnect.product.service.product.product.site.product.detail.SiteProductDetailService;
import com.iconnect.product.service.product.product.site.template.detail.SiteProductTemplateDetailService;
import com.iconnect.product.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerTransactionServiceImpl implements CustomerTransactionService {
    private final CustomerSiteProductRepository customerSiteProductRepository;
    private final CustomerTransactionMappingRepository customerTransactionMappingRepository;
    private final SiteBaseProductSettingDataRepository settingDataRepository;
    private final CustomerTransactionRepository customerTransactionRepository;

    private final SiteProductTemplateDetailService templateDetailService;
    private final SiteProductDetailService siteProductDetailService;
    private final IntegrationUtil integrationUtil;
    private final ObjectMapper mapper;

    @Override
    public Object getData(UUID userId, UUID siteProductId) throws IOException {
        CompanyCustomer entityUserCompany = integrationUtil.getOrCheckCompanyCustomer(userId);
        SiteProductTemplateOutput templateOutput = templateDetailService.getProductTemplate(siteProductId);
        CustomerTransactionMapping transactionMapping = customerTransactionMappingRepository.
                findByCustomerIdAndCompanyId(entityUserCompany.getCustomerId(), entityUserCompany.getCompanyId()).orElse(null);
        if (templateOutput == null) {
            throw new IllegalStateException("Product template not found for siteProductId: " + siteProductId);
        }
        CustomerTransactionDataOutput result = mapper.convertValue(templateOutput, CustomerTransactionDataOutput.class);

        result.getStructures().forEach(structure ->
                structure.getCardTemplate().forEach(cardTemplate -> {
                    SiteBaseProductSettingData settingData = settingDataRepository
                            .findBySettingCodeIgnoreCase(cardTemplate.getSettingCode());
                    cardTemplate.setDescription(settingData.getObjectName());
                })
        );
        if (Objects.isNull(transactionMapping)){
            return result;
        }
        try {
            return setTransactionData(result, templateOutput, transactionMapping);
        }catch (Exception e){
            HashMap<Object, Object> outputFromHashmap = new HashMap<>();
            outputFromHashmap.put("siteProductId", result.getSiteProductId());
            outputFromHashmap.put("structures", result.getStructures());
            outputFromHashmap.put("errorMsg", e.getMessage());
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            return outputFromHashmap ;
        }
    }

    @Override
    public Object getProductList(UUID userId) throws JsonProcessingException {
        CompanyCustomer entityUserCompany = integrationUtil.getOrCheckCompanyCustomer(userId);
        List<IntCompanyCustomerProductMappingOutput> results = new ArrayList<>();
        List<CustomerSiteProduct> customerSiteProducts = customerSiteProductRepository.findAllByCompanyId(entityUserCompany.getCompanyId());
        if (!customerSiteProducts.isEmpty()){
            for (var customerSiteProduct : customerSiteProducts){
                if (customerSiteProduct.getCustomerId().equals(userId)){
                    SiteProductDetailOutput siteProduct = siteProductDetailService.getSiteProductDetail(customerSiteProduct.getSiteProductId());
                    IntCompanyCustomerProductMappingOutput result = new IntCompanyCustomerProductMappingOutput();
                    result.setSiteProductId(customerSiteProduct.getSiteProductId());
                    result.setCustomerId(customerSiteProduct.getCustomerId());
                    result.setProductType(siteProduct.getProductTypeCode());
                    result.setProductName(siteProduct.getSiteProductName());
                    results.add(result);
                }
            }
        }
        return results;
    }

    @Override
    public Object saveData(UUID userId, UUID siteProductId, CustomerTransactionDataInput input) throws JsonProcessingException {
        CompanyCustomer entityUserCompany = integrationUtil.getOrCheckCompanyCustomer(userId);
        CustomerTransactionMapping transactionMapping = customerTransactionMappingRepository.
                findByCustomerIdAndCompanyId(entityUserCompany.getCustomerId(), entityUserCompany.getCompanyId()).orElse(null);
        if (Objects.isNull(transactionMapping)){
            transactionMapping = new CustomerTransactionMapping();
            transactionMapping.setCustomerTransactionMappingId(UUID.randomUUID());
            transactionMapping.setCompanyId(entityUserCompany.getCompanyId());
            transactionMapping.setCustomerId(entityUserCompany.getCustomerId());
            customerTransactionMappingRepository.save(transactionMapping);
        }
        CustomerTransaction data = new CustomerTransaction();
        data.setCustomerTransactionId(UUID.randomUUID());
        data.setCustomerTransactionMappingId(transactionMapping.getCustomerTransactionMappingId());
        data.setCreatedDate(LocalDateTime.now());
        data.setData(StringUtil.byteConvert(input.getData()));
        customerTransactionRepository.save(data);
        return data;
    }
    private CustomerTransactionDataOutput setTransactionData(
            CustomerTransactionDataOutput result,
            SiteProductTemplateOutput templateOutput,
            CustomerTransactionMapping transactionMapping) throws IOException {

        CustomerTransaction data = customerTransactionRepository
                .findTopByCustomerTransactionMappingIdOrderByCreatedDateDesc(
                        transactionMapping.getCustomerTransactionMappingId()).orElse(null);

        if (Objects.isNull(data)) {
            return result;
        }

        CustomerTransactionDataOutput newResult = new CustomerTransactionDataOutput();
        CustomerTransactionDataOutput transactionDataOutput =
                mapper.readValue(data.getData(), CustomerTransactionDataOutput.class);


        newResult.setSiteProductId(templateOutput.getSiteProductId());
        newResult.setStructures(processStructures(templateOutput, transactionDataOutput));

        return newResult;
    }

    private List<CustomerTransactionDataOutput.StructureDTO> processStructures(
            SiteProductTemplateOutput templateOutput,
            CustomerTransactionDataOutput transactionDataOutput) {

        List<CustomerTransactionDataOutput.StructureDTO> newStructures = new ArrayList<>();

        templateOutput.getStructures().forEach(structureTemplate -> {
            if (!structureTemplate.getCardTemplate().isEmpty()){
                CustomerTransactionDataOutput.StructureDTO newStructure = createStructure(structureTemplate);
                newStructure.setCardTemplate(
                        processCardTemplates(structureTemplate, transactionDataOutput)
                );
                newStructures.add(newStructure);
            }
        });

        return newStructures;
    }

    private CustomerTransactionDataOutput.StructureDTO createStructure(
            SiteProductTemplateOutput.StructureDTO structureTemplate) {

        CustomerTransactionDataOutput.StructureDTO newStructure =
                new CustomerTransactionDataOutput.StructureDTO();
        newStructure.setSeq(structureTemplate.getSeq());
        newStructure.setSiteBaseProductStructureName(
                structureTemplate.getSiteBaseProductStructureName()
        );
        return newStructure;
    }

    private List<CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO> processCardTemplates(
            SiteProductTemplateOutput.StructureDTO structureTemplate,
            CustomerTransactionDataOutput transactionDataOutput) {

        List<CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO> newCardTemplates =
                new ArrayList<>();

        structureTemplate.getCardTemplate().forEach(structureCardTemplate -> {
            CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO newCardTemplate =
                    findAndMapCardTemplate(structureCardTemplate, transactionDataOutput);
            newCardTemplates.add(newCardTemplate);
        });

        return newCardTemplates;
    }

    private CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO findAndMapCardTemplate(
            SiteProductTemplateOutput.StructureDTO.CardTemplateDTO structureCardTemplate,
            CustomerTransactionDataOutput transactionDataOutput) {

        CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO newCardTemplate =
                new CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO();

        transactionDataOutput.getStructures().forEach(structure ->
                structure.getCardTemplate().forEach(cardTemplate -> {
                    if (cardTemplate.getSettingCode()
                            .equalsIgnoreCase(structureCardTemplate.getSettingCode())) {
                        mapCardTemplateData(newCardTemplate, cardTemplate, structureCardTemplate);
                    }
                })
        );

        return newCardTemplate;
    }

    private void mapCardTemplateData(
            CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO target,
            CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO source,
            SiteProductTemplateOutput.StructureDTO.CardTemplateDTO cardTemplateDTO) {

        target.setSeq(cardTemplateDTO.getSeq());
        target.setSettingCode(cardTemplateDTO.getSettingCode());
        target.setComponentValue(cardTemplateDTO.getComponentValue());
        target.setValue(source.getValue());
        target.setLowerBond(source.getLowerBond());
        target.setUpperBond(source.getUpperBond());
        target.setInputType(cardTemplateDTO.getInputType());
        SiteBaseProductSettingData settingData = settingDataRepository
                .findBySettingCodeIgnoreCase(source.getSettingCode());
        target.setDescription(settingData.getObjectName());
    }
}

