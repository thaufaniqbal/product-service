package com.banyuijo.product.repository;

import com.banyuijo.product.entity.SiteBaseProductStructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SiteBaseProductStructureRepository extends JpaRepository <SiteBaseProductStructure, UUID> {
    List<SiteBaseProductStructure> findAllBySiteBaseProductParentId (UUID siteBaseProductParentId);
}
