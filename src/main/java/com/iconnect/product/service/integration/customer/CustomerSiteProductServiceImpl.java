package com.iconnect.product.service.integration.customer;

import com.iconnect.product.dto.integration.IntCompanyCustomerProductMappingInput;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.integration.CustomerSiteProduct;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.auth.EntityUserCompanyRepository;
import com.iconnect.product.repository.integration.CustomerSiteProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CustomerSiteProductServiceImpl implements CustomerSiteProductService {
    private final CustomerSiteProductRepository customerSiteProductRepository;
    private final EntityUserCompanyRepository entityUserCompanyRepository;

    @Override
    public Object customerSiteProductMapping(UUID userId, IntCompanyCustomerProductMappingInput input) {
        EntityUserCompany entityUserCompany = getEntityUser(userId);
        CustomerSiteProduct customerSiteProduct = new CustomerSiteProduct();
        customerSiteProduct.setCustomerSiteProductId(UUID.randomUUID());
        customerSiteProduct.setSiteProductId(input.getSiteProductId());
        customerSiteProduct.setCustomerId(input.getCustomerId());
        customerSiteProduct.setCompanyId(entityUserCompany.getCompanyId());
        customerSiteProductRepository.save(customerSiteProduct);
        return true;
    }
    private EntityUserCompany getEntityUser (UUID userId) {
        EntityUserCompany result = entityUserCompanyRepository.findById(userId).orElse(null);
        if (Objects.isNull(result)){
            throw new HttpStatusException(HttpStatusCode.AUTH_DATA_NOT_FOUND);
        }
        return result;
    }
}
