package com.iconnect.product.entity.integration.id;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCustomerIds  implements Serializable {
    @Id
    @Column(name = "company_id")
    private UUID companyId;

    @Id
    @Column(name = "customer_id")
    private UUID customerId;
}
