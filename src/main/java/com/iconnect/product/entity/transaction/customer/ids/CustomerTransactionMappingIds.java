package com.iconnect.product.entity.transaction.customer.ids;

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
public class CustomerTransactionMappingIds implements Serializable {
    @Id
    @Column(name = "customer_transaction_mapping_id")
    private UUID customerTransactionMappingId;

    @Id
    @Column(name = "company_id")
    private UUID companyId;

    @Id
    @Column(name = "customer_id")
    private UUID customerId;
}
