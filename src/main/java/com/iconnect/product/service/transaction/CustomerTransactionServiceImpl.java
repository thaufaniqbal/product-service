package com.iconnect.product.service.transaction;

import com.iconnect.product.dto.transaction.CustomerTransactionDataInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerTransactionServiceImpl implements CustomerTransactionService {

    @Override
    public Object getData(UUID companyId, CustomerTransactionDataInput input) {
        return null;
    }

    @Override
    public Object getProductList(UUID companyId, CustomerTransactionDataInput input) {
        return null;
    }

    @Override
    public Object saveData(CustomerTransactionDataInput input) {
        return null;
    }
}
