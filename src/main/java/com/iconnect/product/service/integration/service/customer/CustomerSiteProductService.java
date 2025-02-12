package com.iconnect.product.service.integration.service.customer;

import com.iconnect.product.dto.integration.IntCompanyCustomerProductMappingInput;

import java.util.UUID;

public interface CustomerSiteProductService {
    Object customerSiteProductMapping (UUID userId, IntCompanyCustomerProductMappingInput input);
}
