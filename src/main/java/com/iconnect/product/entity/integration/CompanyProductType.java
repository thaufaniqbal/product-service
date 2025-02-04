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
@Table(name = "mst_company_product_type")
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProductType {
    @Id
    @Column(name = "company_id", columnDefinition = "char(36)")
    private UUID companyId;

    @Column(name = "product_type_id", columnDefinition = "char(36)")
    private UUID productTypeId;
}
