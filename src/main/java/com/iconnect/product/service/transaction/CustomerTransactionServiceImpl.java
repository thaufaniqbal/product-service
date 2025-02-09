package com.iconnect.product.service.transaction;

import com.iconnect.product.dto.transaction.CustomerTransactionDataInput;
import com.iconnect.product.entity.integration.CompanyCustomer;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.integration.CompanyCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerTransactionServiceImpl implements CustomerTransactionService {
    private final CompanyCustomerRepository companyCustomerRepository;

    @Override
    public Object getData(UUID userId, UUID siteProductId, CustomerTransactionDataInput input) {
        CompanyCustomer entityUserCompany = getCompanyCustomer(userId);

        return null;
    }

    @Override
    public Object getProductList(UUID userId) {
        CompanyCustomer entityUserCompany = getCompanyCustomer(userId);

        return null;
    }

    @Override
    public Object saveData(UUID userId, UUID siteProductId, CustomerTransactionDataInput input) {
        CompanyCustomer entityUserCompany = getCompanyCustomer(userId);

        return null;
    }
    private CompanyCustomer getCompanyCustomer (UUID userId) {
        CompanyCustomer result = companyCustomerRepository.findByCustomerId(userId).orElse(null);
        if (Objects.isNull(result)){
            throw new HttpStatusException(HttpStatusCode.AUTH_DATA_NOT_FOUND);
        }
        return result;
    }
}
