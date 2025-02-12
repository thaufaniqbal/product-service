package com.iconnect.product.service.integration.service.customer;

import com.iconnect.product.dto.integration.IntCompanyCustomerProductMappingInput;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.integration.CustomerSiteProduct;
import com.iconnect.product.repository.integration.CustomerSiteProductRepository;
import com.iconnect.product.service.integration.validator.IntegrationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CustomerSiteProductServiceImpl implements CustomerSiteProductService {
    private final CustomerSiteProductRepository customerSiteProductRepository;
    private final IntegrationUtil integrationUtil;

    @Override
    public Object customerSiteProductMapping(UUID userId, IntCompanyCustomerProductMappingInput input) {
        EntityUserCompany entityUserCompany = integrationUtil.getOrCheckCompanyUser(userId);
        CustomerSiteProduct customerSiteProduct = new CustomerSiteProduct();
        customerSiteProduct.setCustomerSiteProductId(UUID.randomUUID());
        customerSiteProduct.setSiteProductId(input.getSiteProductId());
        customerSiteProduct.setCustomerId(input.getCustomerId());
        customerSiteProduct.setCompanyId(entityUserCompany.getCompanyId());
        customerSiteProductRepository.save(customerSiteProduct);
        return true;
    }
}
