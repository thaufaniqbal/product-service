package com.iconnect.product.service.integration.customer;

import com.iconnect.product.dto.integration.IntCompanyCustomerProductMappingInput;
import com.iconnect.product.entity.integration.CustomerSiteProduct;
import com.iconnect.product.repository.integration.CustomerSiteProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CustomerSiteProductServiceImpl implements CustomerSiteProductService {
    private final CustomerSiteProductRepository customerSiteProductRepository;
    @Override
    public Object customerSiteProductMapping(UUID userId, IntCompanyCustomerProductMappingInput input) {
        CustomerSiteProduct customerSiteProduct = new CustomerSiteProduct();
        customerSiteProduct.setCustomerSiteProductId(UUID.randomUUID());
        customerSiteProduct.setSiteProductId(input.getSiteProductId());
        customerSiteProduct.setCustomerId(input.getCustomerId());
        customerSiteProductRepository.save(customerSiteProduct);
        return true;
    }
}
