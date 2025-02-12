package com.iconnect.product.entity.transaction.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "trx_company_customer_mapping")
@AllArgsConstructor
@NoArgsConstructor
public class TrxCompanyCustomerMapping {
    @Id
    @Column(name = "company_customer_mapping_id")
    private UUID companyCustomerMappingId;

    @Column(name = "company_id")
    private UUID companyId;

    @Column(name = "customer_Id")
    private UUID customerId;

    @Column(name = "site_product_id")
    private UUID siteProductId;

    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;
}
