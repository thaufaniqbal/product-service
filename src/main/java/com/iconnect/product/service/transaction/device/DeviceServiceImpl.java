package com.iconnect.product.service.transaction.device;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iconnect.product.dto.product.site.structure.detail.SiteProductDetailStructureOutput;
import com.iconnect.product.dto.transaction.DeviceTransactionInputOutput;
import com.iconnect.product.entity.integration.CompanyCustomer;
import com.iconnect.product.entity.transaction.device.DeviceTransaction;
import com.iconnect.product.entity.transaction.device.DeviceTransactionMapping;
import com.iconnect.product.repository.transaction.device.DeviceTransactionMappingRepository;
import com.iconnect.product.repository.transaction.device.DeviceTransactionRepository;
import com.iconnect.product.service.integration.validator.IntegrationUtil;
import com.iconnect.product.service.product.product.site.structure.detail.SiteProductDetailStructureService;
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
public class DeviceServiceImpl implements DeviceService {
    private final DeviceTransactionRepository deviceTransactionRepository;
    private final DeviceTransactionMappingRepository deviceTransactionMappingRepository;
    private final SiteProductDetailStructureService siteProductDetailStructureService;
    private final IntegrationUtil integrationUtil;
    private final ObjectMapper objectMapper;

    @Override
    public Object getData(UUID userId, UUID siteProductId) throws IOException {
        CompanyCustomer customer = integrationUtil.getOrCheckCompanyCustomer(userId);
        SiteProductDetailStructureOutput structureDetails = siteProductDetailStructureService.getSiteProductStructureDetail(siteProductId);

        DeviceTransactionMapping transactionMapping = deviceTransactionMappingRepository
                .findByCustomerIdAndCompanyId(customer.getCustomerId(), customer.getCompanyId())
                .orElse(null);

        DeviceTransactionInputOutput result = buildBaseResult(structureDetails);

        if (Objects.isNull(transactionMapping)) {
            return result;
        }

        DeviceTransaction latestTransaction = deviceTransactionRepository
                .findTopByDeviceTransactionMappingIdOrderByCreatedDateDesc(
                        transactionMapping.getDeviceTransactionMappingId())
                .orElse(null);

        if (Objects.isNull(latestTransaction) || Objects.isNull(latestTransaction.getData())) {
            return result;
        }

        return mergeTransactionData(result, latestTransaction);
    }

    @Override
    public Object saveData(UUID userId, DeviceTransactionInputOutput input) throws JsonProcessingException {
        CompanyCustomer customer = integrationUtil.getOrCheckCompanyCustomer(userId);
        DeviceTransactionMapping transactionMapping = deviceTransactionMappingRepository
                .findByCustomerIdAndCompanyId(customer.getCustomerId(), customer.getCompanyId())
                .orElse(null);

        if (Objects.isNull(transactionMapping)) {
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

    private DeviceTransactionInputOutput buildBaseResult(SiteProductDetailStructureOutput structureDetails) {
        List<DeviceTransactionInputOutput.Data> resultData = new ArrayList<>();

        structureDetails.getStructure().getStructures().stream()
                .flatMap(structure -> structure.getSettings().stream())
                .forEach(setting -> {
                    DeviceTransactionInputOutput.Data data = new DeviceTransactionInputOutput.Data();
                    DeviceTransactionInputOutput.Data.DataValue dataValue = new DeviceTransactionInputOutput.Data.DataValue();
                    dataValue.setValue(null);
                    dataValue.setLowerBond(setting.getLowerBond());
                    dataValue.setUpperBond(setting.getUpperBond());
                    data.setSettingCode(setting.getSettingCode());
                    data.setDataValue(dataValue);
                    resultData.add(data);
                });

        DeviceTransactionInputOutput result = new DeviceTransactionInputOutput();
        result.setSiteProductId(structureDetails.getSiteProductId());
        result.setData(resultData);
        return result;
    }


    private DeviceTransactionInputOutput mergeTransactionData(
            DeviceTransactionInputOutput baseResult,
            DeviceTransaction latestTransaction) throws IOException {

        DeviceTransactionInputOutput savedData = objectMapper.readValue(
                latestTransaction.getData(),
                DeviceTransactionInputOutput.class);

        if (savedData == null || savedData.getData() == null) {
            return baseResult;
        }

        Map<String, DeviceTransactionInputOutput.Data> savedDataMap = new HashMap<>();
        for (DeviceTransactionInputOutput.Data data : savedData.getData()) {
            if (data.getSettingCode() != null) {
                savedDataMap.put(data.getSettingCode(), data);
            }
        }

        for (DeviceTransactionInputOutput.Data baseData : baseResult.getData()) {
            DeviceTransactionInputOutput.Data savedItem = savedDataMap.get(baseData.getSettingCode());
            if (savedItem != null && savedItem.getDataValue() != null) {
                DeviceTransactionInputOutput.Data.DataValue savedValue = savedItem.getDataValue();
                DeviceTransactionInputOutput.Data.DataValue baseValue = baseData.getDataValue();

                // Update with saved values
                if (savedValue.getValue() != null) {
                    baseValue.setValue(savedValue.getValue());
                }
                if (savedValue.getUpperBond() != null) {
                    baseValue.setUpperBond(savedValue.getUpperBond());
                }
                if (savedValue.getLowerBond() != null) {
                    baseValue.setLowerBond(savedValue.getLowerBond());
                }
            }
        }

        return baseResult;
    }
}