package com.banyuijo.product.repository.customer;

import com.banyuijo.product.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
