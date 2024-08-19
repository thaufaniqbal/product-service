package com.banyuijo.product.repository.customer;

import com.banyuijo.product.entity.customer.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}
