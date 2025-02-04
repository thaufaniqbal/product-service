package com.iconnect.product.repository.product;

import com.iconnect.product.entity.product.SiteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SiteProductRepository extends JpaRepository <SiteProduct, UUID> {

    Boolean existsBySiteProductCodeIgnoreCaseAndDeleteStatus(String siteProductCode, int deleteStatus);

    SiteProduct findBySiteProductId (UUID siteProductId);

    List<SiteProduct> findAllByProductTypeIdAndDeleteStatus(UUID productTypeId, Integer deleteStatus);
    List<SiteProduct> findAllByDeleteStatus(Integer deleteStatus);
}
