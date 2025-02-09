package com.iconnect.product.service.integration.customer;

import com.iconnect.product.dto.integration.IntCompanyCustomerProductMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CustomerSiteProductServiceImpl implements CustomerSiteProductService {
    @Override
    public Object customerSiteProductMapping(UUID userId, IntCompanyCustomerProductMapping input) {
        return null;
    }
}
