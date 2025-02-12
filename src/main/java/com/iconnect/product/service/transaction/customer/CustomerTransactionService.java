package com.iconnect.product.service.transaction.customer;

import com.iconnect.product.dto.transaction.CustomerTransactionDataInput;

import java.util.UUID;

public interface CustomerTransactionService {
    Object getData (UUID userId, UUID siteProductId);
    Object getProductList (UUID userId);
    Object saveData (UUID userId, UUID siteProductId, CustomerTransactionDataInput input);
}
