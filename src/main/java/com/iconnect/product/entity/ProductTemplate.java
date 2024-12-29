package com.iconnect.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_product_template")
@AllArgsConstructor
@NoArgsConstructor
public class ProductTemplate {
    @Id
    @Column(name = "product_template_id", columnDefinition = "char(36)")
    private UUID productTemplateId;

    @Column(name = "site_product_Id", columnDefinition = "char(36)")
    private UUID siteProductId;
}
