package com.banyuijo.product.repository;

import com.banyuijo.product.entity.SiteBaseProductParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SiteBaseProductParentRepository extends JpaRepository <SiteBaseProductParent, UUID> {
    SiteBaseProductParent findBySiteProductId (UUID siteProductId);

}
