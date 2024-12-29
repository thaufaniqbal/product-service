package com.iconnect.product.repository.customer;

import com.iconnect.product.entity.customer.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository <Address, UUID> {

}
