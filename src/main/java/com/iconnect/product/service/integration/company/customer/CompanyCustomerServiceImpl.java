package com.iconnect.product.service.integration.company.customer;

import com.iconnect.product.dto.integration.IntCompanyCustomerCredentialOutput;
import com.iconnect.product.dto.integration.IntCompanyCustomerInput;
import com.iconnect.product.dto.integration.IntCompanyCustomerSearchInput;
import com.iconnect.product.dto.integration.IntCompanyCustomerSearchOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CompanyCustomerServiceImpl implements CompanyCustomerService {
    @Override
    public Object createCustomerByCompany(UUID userId, IntCompanyCustomerInput input) {
        return null;
    }

    @Override
    public IntCompanyCustomerSearchOutput searchCustomer(UUID userId, IntCompanyCustomerSearchInput input) {
        return null;
    }

    @Override
    public IntCompanyCustomerCredentialOutput getCustomerCredential(UUID userId, UUID customerId) {
        return null;
    }

    @Override
    public Object getCustomerProductMapping(UUID userId, UUID customerId) {
        return null;
    }
}
