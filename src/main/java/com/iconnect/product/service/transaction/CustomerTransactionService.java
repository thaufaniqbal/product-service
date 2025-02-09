package com.iconnect.product.service.transaction;

import com.iconnect.product.dto.transaction.CustomerTransactionDataInput;

import java.util.UUID;

public interface CustomerTransactionService {
    Object getData (UUID companyId, CustomerTransactionDataInput input);
    Object getProductList (UUID companyId, CustomerTransactionDataInput input);
    Object saveData (CustomerTransactionDataInput input);
}
