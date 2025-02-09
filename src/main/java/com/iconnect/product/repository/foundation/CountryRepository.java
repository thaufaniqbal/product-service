package com.iconnect.product.repository.foundation;

import com.iconnect.product.entity.foundation.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}
