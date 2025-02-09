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
@Table(name = "mst_company_customer")
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCustomer {
    @Id
    @Column(name = "company_id")
    private UUID companyId;

    @Column(name = "customer_id")
    private UUID customerId;
}
