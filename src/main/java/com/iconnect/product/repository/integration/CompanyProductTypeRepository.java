package com.iconnect.product.repository.integration;

import com.iconnect.product.entity.integration.CompanyProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyProductTypeRepository extends JpaRepository<CompanyProductType, UUID> {
}
