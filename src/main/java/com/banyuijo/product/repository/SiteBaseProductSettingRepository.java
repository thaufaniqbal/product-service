package com.banyuijo.product.repository;

import com.banyuijo.product.entity.SiteBaseProductSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SiteBaseProductSettingRepository extends JpaRepository <SiteBaseProductSetting, UUID> {
    List<SiteBaseProductSetting> findAllBySiteBaseProductStructureId(UUID siteBaseProductStructureId);

}
