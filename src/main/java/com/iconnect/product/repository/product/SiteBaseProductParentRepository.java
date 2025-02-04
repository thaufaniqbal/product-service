package com.iconnect.product.repository.product;

import com.iconnect.product.entity.product.SiteBaseProductParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SiteBaseProductParentRepository extends JpaRepository <SiteBaseProductParent, UUID> {
    SiteBaseProductParent findBySiteProductId (UUID siteProductId);

}
