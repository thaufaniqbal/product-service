package com.iconnect.product.repository;

import com.iconnect.product.entity.ProductTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductTemplateRepository extends JpaRepository<ProductTemplate, UUID> {
    Optional<ProductTemplate> findBySiteProductId(UUID siteProductId);
}
