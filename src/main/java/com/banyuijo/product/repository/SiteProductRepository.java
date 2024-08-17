package com.banyuijo.product.repository;

import com.banyuijo.product.entity.SiteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SiteProductRepository extends JpaRepository <SiteProduct, UUID> {

    Boolean existsBySiteProductCodeIgnoreCase(String siteProductCode);

    SiteProduct findBySiteProductId (UUID siteProductId);

    List<SiteProduct> findAllByProductTypeIdAndDeleteStatus(UUID productTypeId, Integer deleteStatus);
    List<SiteProduct> findAllByDeleteStatus(Integer deleteStatus);
}