package com.banyuijo.foundation.repository;

import com.banyuijo.foundation.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}
