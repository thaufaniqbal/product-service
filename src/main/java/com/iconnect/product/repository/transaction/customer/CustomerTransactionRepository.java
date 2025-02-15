package com.iconnect.product.repository.transaction.customer;

import com.iconnect.product.entity.transaction.customer.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, UUID> {
    CustomerTransaction findTopByCustomerTransactionMappingIdOrderByCreatedDateDesc(
            UUID customerTransactionMappingId
    );}
