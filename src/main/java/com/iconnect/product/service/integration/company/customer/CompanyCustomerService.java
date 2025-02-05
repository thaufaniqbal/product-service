package com.iconnect.product.service.integration.company.customer;

import com.iconnect.product.dto.integration.IntCompanyCustomerInput;

import java.util.UUID;

public interface CompanyCustomerService {
    Object createCustomerByCompany (UUID userId, IntCompanyCustomerInput input);

}
