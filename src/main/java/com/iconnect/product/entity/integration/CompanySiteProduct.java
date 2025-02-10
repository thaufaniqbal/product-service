package com.iconnect.product.entity.integration;

import com.iconnect.product.entity.integration.id.CompanySiteProductIds;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_company_site_product")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CompanySiteProductIds.class)
public class CompanySiteProduct {
    @Id
    @Column(name = "company_id")
    private UUID companyId;

    @Id
    @Column(name = "site_product_id")
    private UUID siteProductId;
}
