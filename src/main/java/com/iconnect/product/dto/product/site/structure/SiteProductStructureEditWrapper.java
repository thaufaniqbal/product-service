package com.iconnect.product.dto.product.site.structure;

import com.iconnect.product.entity.product.SiteBaseProductSetting;
import com.iconnect.product.entity.product.SiteBaseProductSettingData;
import com.iconnect.product.entity.product.SiteBaseProductStructure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductStructureEditWrapper {
    List<SiteBaseProductStructure> structures;
    List<SiteBaseProductSetting> settings;
    List<SiteBaseProductSettingData> settingData;
}
