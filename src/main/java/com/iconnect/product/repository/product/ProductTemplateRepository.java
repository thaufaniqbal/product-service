package com.iconnect.product.repository.product;

import com.iconnect.product.entity.product.ProductTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductTemplateRepository extends JpaRepository<ProductTemplate, UUID> {
    Optional<ProductTemplate> findBySiteProductId(UUID siteProductId);
}
