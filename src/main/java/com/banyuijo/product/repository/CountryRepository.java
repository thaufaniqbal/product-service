package com.banyuijo.product.repository;

import com.banyuijo.product.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}
