package com.banyuijo.foundation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_site_base_product_setting_data")
public class SiteBaseProductSettingData {
    @Id
    @Column(name = "site_base_product_setting_data_id")
    private UUID siteBaseProductSettingDataId;

    @Column(name = "site_base_product_setting_id")
    private UUID siteBaseProductSettingId;

    @Column(name = "seq")
    private Integer seq;

    @Column(name = "value")
    private String value;

    @Column(name = "upper_bond")
    private String upperBond;

    @Column(name = "lower_bond")
    private String lowerBond;
}
