package com.iconnect.product.repository.integration;

import com.iconnect.product.entity.integration.CompanySiteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanySiteProductRepository extends JpaRepository<CompanySiteProduct, UUID> {
}
