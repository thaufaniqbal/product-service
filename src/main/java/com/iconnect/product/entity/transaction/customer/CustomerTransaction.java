package com.iconnect.product.entity.transaction.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "trx_customer_transaction")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTransaction {
    @Id
    @Column(name = "customer_transaction_id")
    private UUID customerTransactionId;

    @Column(name = "customer_transaction_mapping_id")
    private UUID customerTransactionMappingId;

    @Column(name = "data")
    @Lob
    private String data;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
