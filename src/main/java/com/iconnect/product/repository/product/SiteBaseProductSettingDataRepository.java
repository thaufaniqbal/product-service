package com.iconnect.product.repository.product;

import com.iconnect.product.entity.product.SiteBaseProductSettingData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SiteBaseProductSettingDataRepository extends JpaRepository <SiteBaseProductSettingData, UUID> {
    List<SiteBaseProductSettingData> findAllBySiteBaseProductSettingId(UUID siteBaseProductSettingId);
    SiteBaseProductSettingData findBySettingCodeIgnoreCase(String settingCode);
}
