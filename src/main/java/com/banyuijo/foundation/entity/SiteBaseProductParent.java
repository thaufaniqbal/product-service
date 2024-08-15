package com.banyuijo.foundation.entity;

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
@Table(name = "mst_site_base_product_parent")
public class SiteBaseProductParent {

    @Id
    @Column(name = "site_base_product_parent_id")
    private UUID siteBaseProductParentId;

    @Column(name = "site_base_product_parent_name")
    private String siteBaseProductParentName;

    @Column(name = "site_product_Id")
    private UUID siteProductId;

    @Column(name = "product_type_Id")
    private UUID productTypeId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

}