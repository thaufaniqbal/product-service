package com.iconnect.product.entity.integration;

import com.iconnect.product.entity.integration.id.CompanyCustomerIds;
import jakarta.persistence.*;
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
@IdClass(CompanyCustomerIds.class)
public class CompanyCustomer {
    @Id
    @Column(name = "company_id")
    private UUID companyId;

    @Id
    @Column(name = "customer_id")
    private UUID customerId;
}
