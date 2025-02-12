package com.iconnect.product.service.integration.validator;

import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.integration.CompanyCustomer;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.auth.EntityUserCompanyRepository;
import com.iconnect.product.repository.integration.CompanyCustomerRepository;
import com.iconnect.product.validator.GlobalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IntegrationUtil extends GlobalValidator {
    private final CompanyCustomerRepository companyCustomerRepository;
    private final EntityUserCompanyRepository entityUserCompanyRepository;

    public EntityUserCompany getOrCheckCompanyUser (UUID userId) {
        EntityUserCompany result = entityUserCompanyRepository.findById(userId).orElse(null);
        if (Objects.isNull(result)){
            throw new HttpStatusException(HttpStatusCode.AUTH_DATA_NOT_FOUND);
        }
        return result;
    }
    public CompanyCustomer getOrCheckCompanyCustomer (UUID userId) {
        CompanyCustomer result = companyCustomerRepository.findByCustomerId(userId).orElse(null);
        if (Objects.isNull(result)){
            throw new HttpStatusException(HttpStatusCode.AUTH_DATA_NOT_FOUND);
        }
        return result;
    }
}
