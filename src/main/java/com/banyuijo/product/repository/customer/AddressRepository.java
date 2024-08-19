package com.banyuijo.product.repository.customer;

import com.banyuijo.product.entity.customer.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository <Address, UUID> {

}
