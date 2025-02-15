package com.iconnect.product.service.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iconnect.product.dto.integration.IntCompanyCustomerProductMappingOutput;
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
import com.iconnect.product.service.product.product.site.template.detail.SiteProductTemplateDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerTransactionServiceImpl implements CustomerTransactionService {
    private final CustomerSiteProductRepository customerSiteProductRepository;
    private final CustomerTransactionMappingRepository customerTransactionMappingRepository;
    private final SiteBaseProductSettingDataRepository settingDataRepository;
    private final CustomerTransactionRepository customerTransactionRepository;

    private final SiteProductTemplateDetailService templateDetailService;
    private final IntegrationUtil integrationUtil;
    private final ObjectMapper mapper;

    @Override
    public Object getData(UUID userId, UUID siteProductId) throws IOException {
        CompanyCustomer entityUserCompany = integrationUtil.getOrCheckCompanyCustomer(userId);
        CustomerTransactionDataOutput result = new CustomerTransactionDataOutput();
        SiteProductTemplateOutput templateOutput = templateDetailService.getProductTemplate(siteProductId);
        CustomerTransactionMapping transactionMapping = customerTransactionMappingRepository.
                findByCustomerIdAndCompanyId(entityUserCompany.getCustomerId(), entityUserCompany.getCompanyId()).orElse(null);
        BeanUtils.copyProperties(templateOutput, result);
//        result.getStructures().forEach(structure ->
//                structure.getCardTemplate().forEach(cardTemplate -> {
//                    SiteBaseProductSettingData settingData = settingDataRepository
//                            .findBySettingCodeIgnoreCase(cardTemplate.getSettingCode());
//                    cardTemplate.setDescription(settingData.getObjectName());
//                })
//        );
        if (Objects.isNull(transactionMapping)){
            return result;
        }
        setTransactionData(result, templateOutput, transactionMapping);
        return result;
    }

    @Override
    public Object getProductList(UUID userId) {
        CompanyCustomer entityUserCompany = integrationUtil.getOrCheckCompanyCustomer(userId);
        List<IntCompanyCustomerProductMappingOutput> results = new ArrayList<>();
        List<CustomerSiteProduct> customerSiteProducts = customerSiteProductRepository.findAllByCompanyId(entityUserCompany.getCompanyId());
        if (!customerSiteProducts.isEmpty()){
            for (var customerSiteProduct : customerSiteProducts){
                if (customerSiteProduct.getCustomerId().equals(userId)){
                    IntCompanyCustomerProductMappingOutput result = new IntCompanyCustomerProductMappingOutput();
                    result.setSiteProductId(customerSiteProduct.getSiteProductId());
                    result.setCustomerId(customerSiteProduct.getCustomerId());
                    result.setProductType(null);
                    result.setProductName(null);
                    results.add(result);
                }
            }
        }
        return results;
    }

    @Override
    public Object saveData(UUID userId, UUID siteProductId, CustomerTransactionDataInput input) {
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
        data.setData(input.getData());
        customerTransactionRepository.save(data);
        return data;
    }
    private void setTransactionData (CustomerTransactionDataOutput result,
                                     SiteProductTemplateOutput templateOutput,
                                     CustomerTransactionMapping transactionMapping) throws IOException {
        CustomerTransaction data = customerTransactionRepository.
                findTopByCustomerTransactionMappingIdOrderByCreatedDateDesc(transactionMapping.getCustomerTransactionMappingId());
        if (Objects.nonNull(data)){
            CustomerTransactionDataOutput transactionDataOutput = mapper.readValue(data.getData(), CustomerTransactionDataOutput.class);
            BeanUtils.copyProperties(transactionDataOutput, result);
        }
    }
}

