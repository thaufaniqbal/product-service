package com.iconnect.product.service.transaction.device;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.product.site.structure.detail.SiteProductDetailStructureOutput;
import com.iconnect.product.dto.transaction.CustomerTransactionDataOutput;
import com.iconnect.product.dto.transaction.DeviceTransactionInputOutput;
import com.iconnect.product.entity.integration.CompanyCustomer;
import com.iconnect.product.entity.transaction.device.DeviceTransaction;
import com.iconnect.product.entity.transaction.device.DeviceTransactionMapping;
import com.iconnect.product.repository.transaction.device.DeviceTransactionMappingRepository;
import com.iconnect.product.repository.transaction.device.DeviceTransactionRepository;
import com.iconnect.product.service.integration.validator.IntegrationUtil;
import com.iconnect.product.service.product.product.site.structure.detail.SiteProductDetailStructureService;
import com.iconnect.product.service.transaction.customer.CustomerTransactionService;
import com.iconnect.product.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceServiceImpl implements DeviceService {
    private final DeviceTransactionRepository deviceTransactionRepository;
    private final DeviceTransactionMappingRepository deviceTransactionMappingRepository;
    private final CustomerTransactionService customerTransactionService;
    private final SiteProductDetailStructureService siteProductDetailStructureService;
    private final IntegrationUtil integrationUtil;

    @Override
    public Object getData(UUID userId, UUID siteProductId) throws IOException {
        CompanyCustomer customer = integrationUtil.getOrCheckCompanyCustomer(userId);
        SiteProductDetailStructureOutput structureDetails = siteProductDetailStructureService.getSiteProductStructureDetail(siteProductId);
        CustomerTransactionDataOutput transactionDataOutput =
                (CustomerTransactionDataOutput) customerTransactionService.getData(customer.getCustomerId(), siteProductId);
        return convert(structureDetails, transactionDataOutput);
    }

    @Override
    public Object saveData(UUID userId, DeviceTransactionInputOutput input) throws JsonProcessingException {
        CompanyCustomer customer = integrationUtil.getOrCheckCompanyCustomer(userId);
        DeviceTransactionMapping transactionMapping = deviceTransactionMappingRepository.
                findByCustomerIdAndCompanyId(customer.getCustomerId(), customer.getCompanyId()).orElse(null);
        if (Objects.isNull(transactionMapping)){
            transactionMapping = new DeviceTransactionMapping();
            transactionMapping.setDeviceTransactionMappingId(UUID.randomUUID());
            transactionMapping.setCompanyId(customer.getCompanyId());
            transactionMapping.setCustomerId(customer.getCustomerId());
            deviceTransactionMappingRepository.save(transactionMapping);
        }
        DeviceTransaction data = new DeviceTransaction();
        data.setDeviceTransactionId(UUID.randomUUID());
        data.setDeviceTransactionMappingId(transactionMapping.getDeviceTransactionMappingId());
        data.setCreatedDate(LocalDateTime.now());
        data.setData(StringUtil.byteConvert(input));
        deviceTransactionRepository.save(data);
        return data;
    }

    private DeviceTransactionInputOutput convert(
            SiteProductDetailStructureOutput structureDetails,
            CustomerTransactionDataOutput transactionData
    ) {
        List<DeviceTransactionInputOutput.Data> resultData = new ArrayList<>();

        Map<String, List<CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO>> transactionSettingsMap = transactionData.getStructures().stream()
                .flatMap(structure -> structure.getCardTemplate().stream())
                .collect(Collectors.groupingBy(
                        CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO::getSettingCode,
                        HashMap::new,
                        Collectors.toList()
                ));

        structureDetails.getStructure().getStructures().stream()
                .flatMap(structure -> structure.getSettings().stream())
                .forEach(setting -> {
                    String settingCode = setting.getSettingCode();
                    List<CustomerTransactionDataOutput.StructureDTO.CardTemplateDTO> matchingTemplates = transactionSettingsMap.get(settingCode);

                    if (matchingTemplates != null && !matchingTemplates.isEmpty()) {
                        var transactionSetting = matchingTemplates.get(0);
                        DeviceTransactionInputOutput.Data data = new DeviceTransactionInputOutput.Data();
                        DeviceTransactionInputOutput.Data.DataValue dataValue = new DeviceTransactionInputOutput.Data.DataValue();
                        dataValue.setValue(transactionSetting.getValue());
                        dataValue.setLowerBond(transactionSetting.getLowerBond());
                        dataValue.setUpperBond(transactionSetting.getUpperBond());
                        data.setSettingCode(settingCode);
                        data.setDataValue(dataValue);
                        resultData.add(data);
                    }
                });

        DeviceTransactionInputOutput result = new DeviceTransactionInputOutput();
        result.setSiteProductId(structureDetails.getSiteProductId());
        result.setData(resultMap);

        return result;
    }
}
