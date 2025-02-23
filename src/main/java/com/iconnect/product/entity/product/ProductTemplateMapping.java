package com.iconnect.product.entity.product;

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
@Table(name = "mst_product_template_mapping")
public class ProductTemplateMapping {
    @Id
    @Column(name = "product_template_mapping_id")
    private UUID productTemplateMappingId;

    @Column(name = "product_template_id")
    private UUID productTemplateId;

    @Column(name = "seq")
    private Integer seq;

    @Column(name = "component_value")
    private String componentValue;

    @Column(name = "setting_data_code")
    private String settingDataCode;

    @Column(name = "input_type")
    private Integer inputType;
}
