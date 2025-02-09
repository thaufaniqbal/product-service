package com.iconnect.product.repository.integration;

import com.iconnect.product.entity.integration.CompanySiteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanySiteProductRepository extends JpaRepository<CompanySiteProduct, UUID> {
    List<CompanySiteProduct> findAllByCompanyId(UUID companyId);
}
