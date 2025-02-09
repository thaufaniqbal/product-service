package com.iconnect.product.entity.integration;

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
@Table(name = "mst_customer_site_product")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSiteProduct {
    @Id
    @Column(name = "customer_site_product_id")
    private UUID customerSiteProductId;

    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "site_product_id")
    private UUID siteProductId;

    @Column(name = "company_id")
    private UUID companyId;
}
