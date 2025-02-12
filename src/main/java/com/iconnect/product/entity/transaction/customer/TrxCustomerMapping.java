package com.iconnect.product.entity.transaction.customer;

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
@Table(name = "trx_customer_mapping")
@AllArgsConstructor
@NoArgsConstructor
public class TrxCustomerMapping {
    @Id
    @Column(name = "customer_mapping_id")
    private UUID customerMappingId;

    @Column(name = "company_customer_mapping_id")
    private UUID companyCustomerMappingId;
}
