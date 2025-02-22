package com.iconnect.product.service.transaction.device;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.transaction.DeviceTransactionInputOutput;

import java.io.IOException;
import java.util.UUID;

public interface DeviceService {
    Object getData (UUID userId, UUID siteProductId) throws IOException;
    Object saveData (UUID userId, DeviceTransactionInputOutput input) throws JsonProcessingException;
}
