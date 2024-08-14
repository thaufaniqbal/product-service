package com.banyuijo.foundation.repository;

import com.banyuijo.foundation.entity.SiteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SiteProductRepository extends JpaRepository <SiteProduct, UUID> {

    Boolean existsBySiteProductCodeIgnoreCase(String siteProductCode);

}
