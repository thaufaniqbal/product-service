package com.iconnect.product.service.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.transaction.CustomerTransactionDataInput;

import java.io.IOException;
import java.util.UUID;

public interface CustomerTransactionService {
    Object getData (UUID userId, UUID siteProductId) throws IOException;
    Object getProductList (UUID userId);
    Object saveData (UUID userId, UUID siteProductId, CustomerTransactionDataInput input) throws JsonProcessingException;
}
