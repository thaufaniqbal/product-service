package com.iconnect.product.repository.customer;

import com.iconnect.product.entity.customer.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}
