package com.banyuijo.foundation.repository;

import com.banyuijo.foundation.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository <Address, UUID> {

}
