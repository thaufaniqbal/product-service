package com.iconnect.product.repository.integration;

import com.iconnect.product.entity.integration.CustomerSiteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerSiteProductRepository extends JpaRepository<CustomerSiteProduct, UUID> {
}
