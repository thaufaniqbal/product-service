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
@Table(name = "mst_site_product")
public class SiteProduct {

    @Id
    @Column(name = "site_product_id")
    private UUID siteProductId;

    @Column(name = "site_product_name")
    private String siteProductName;

    @Column(name = "site_product_code")
    private String siteProductCode;

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

    @Column(name = "delete_status")
    private Integer deleteStatus;

}
