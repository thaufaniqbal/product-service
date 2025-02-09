package com.iconnect.product.service.integration.company.customer;

import com.iconnect.product.dto.integration.IntCompanyCustomerInput;
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
}
