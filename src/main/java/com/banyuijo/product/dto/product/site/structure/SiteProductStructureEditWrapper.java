package com.banyuijo.product.dto.product.site.structure;

import com.banyuijo.product.entity.SiteBaseProductSetting;
import com.banyuijo.product.entity.SiteBaseProductSettingData;
import com.banyuijo.product.entity.SiteBaseProductStructure;
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
