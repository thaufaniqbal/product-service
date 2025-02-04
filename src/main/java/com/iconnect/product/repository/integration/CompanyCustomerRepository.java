package com.iconnect.product.repository.integration;

import com.iconnect.product.entity.integration.CompanyCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyCustomerRepository extends JpaRepository<CompanyCustomer, UUID> {
}
