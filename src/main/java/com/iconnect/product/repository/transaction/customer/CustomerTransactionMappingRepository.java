package com.iconnect.product.repository.transaction.customer;

import com.iconnect.product.entity.transaction.customer.CustomerTransactionMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerTransactionMappingRepository extends JpaRepository<CustomerTransactionMapping, UUID> {

    Optional<CustomerTransactionMapping> findByCustomerIdAndCompanyId(UUID customerId, UUID companyId);
}
