package com.iconnect.product.entity.integration;

import com.iconnect.product.entity.integration.id.CompanyProductTypeIds;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_company_product_type")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CompanyProductTypeIds.class)
public class CompanyProductType {
    @Id
    @Column(name = "company_id")
    private UUID companyId;

    @Id
    @Column(name = "product_type_id")
    private UUID productTypeId;
}
