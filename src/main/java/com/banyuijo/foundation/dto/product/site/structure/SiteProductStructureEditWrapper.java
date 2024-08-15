package com.banyuijo.foundation.dto.product.site.structure;

import com.banyuijo.foundation.entity.SiteBaseProductSetting;
import com.banyuijo.foundation.entity.SiteBaseProductSettingData;
import com.banyuijo.foundation.entity.SiteBaseProductStructure;
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
