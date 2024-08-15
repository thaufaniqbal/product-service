package com.banyuijo.foundation.repository;

import com.banyuijo.foundation.entity.SiteBaseProductSettingData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SiteBaseProductSettingDataRepository extends JpaRepository <SiteBaseProductSettingData, UUID> {
}
