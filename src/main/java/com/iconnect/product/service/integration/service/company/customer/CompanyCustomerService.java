package com.iconnect.product.service.integration.service.company.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.integration.IntCompanyCustomerCredentialOutput;
import com.iconnect.product.dto.integration.IntCompanyCustomerInput;
import com.iconnect.product.dto.integration.IntCompanyCustomerSearchInput;

import java.util.UUID;

public interface CompanyCustomerService {
    Object createCustomerByCompany (UUID userId, IntCompanyCustomerInput input);
    Object searchCustomer (UUID userId, IntCompanyCustomerSearchInput input);
    IntCompanyCustomerCredentialOutput getCustomerCredential(UUID userId, UUID customerId);
    Object getCustomerProductMapping(UUID userId, UUID customerId) throws JsonProcessingException;
}
