package com.iconnect.product.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_site_base_product_structure")
public class SiteBaseProductStructure {

    @Id
    @Column(name = "site_base_product_structure_id")
    private UUID siteBaseProductStructureId;

    @Column(name = "site_base_product_structure_name")
    private String siteBaseProductStructureName;

    @Column(name = "site_base_product_parent_id")
    private UUID siteBaseProductParentId;

    @Column(name = "seq")
    private Integer seq;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

}
