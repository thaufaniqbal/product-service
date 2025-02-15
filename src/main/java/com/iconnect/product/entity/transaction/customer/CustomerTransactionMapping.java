package com.iconnect.product.entity.transaction.customer;

import com.iconnect.product.entity.transaction.customer.ids.CustomerTransactionMappingIds;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "trx_customer_transaction_mapping")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CustomerTransactionMappingIds.class)
public class CustomerTransactionMapping {
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
