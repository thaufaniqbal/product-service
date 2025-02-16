package com.iconnect.product.repository.integration;

import com.iconnect.product.entity.integration.CustomerSiteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerSiteProductRepository extends JpaRepository<CustomerSiteProduct, UUID> {
    List<CustomerSiteProduct> findAllByCompanyId(UUID companyId);
    Optional<CustomerSiteProduct> findByCustomerIdAndSiteProductId (UUID customerId, UUID siteProductId);
}
