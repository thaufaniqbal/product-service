package com.iconnect.product.repository.integration;

import com.iconnect.product.entity.integration.CompanyCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyCustomerRepository extends JpaRepository<CompanyCustomer, UUID> {
    List<CompanyCustomer> findAllByCompanyId (UUID companyId);
    Optional<CompanyCustomer> findByCustomerId (UUID customerId);
}
