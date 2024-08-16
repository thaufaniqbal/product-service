package com.banyuijo.product.entity;

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
@Table(name = "mst_product_type")
public class ProductType {

    @Id
    @Column(name = "product_type_Id")
    private UUID productTypeId;

    @Column(name = "product_type_name")
    private String productTypeName;

    @Column(name = "product_type_code")
    private String productTypeCode;

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
