package com.banyuijo.foundation.repository;

import com.banyuijo.foundation.entity.SiteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SiteProductRepository extends JpaRepository <SiteProduct, UUID> {

    Boolean existsBySiteProductCodeIgnoreCase(String siteProductCode);

    SiteProduct findBySiteProductId (UUID siteProductId);

    List<SiteProduct> findAllByProductTypeId(UUID productTypeId);
}
