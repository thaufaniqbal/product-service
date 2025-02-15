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
@Table(name = "mst_company_customer")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTransaction {
    @Id
    @Column(name = "customer_transaction_id")
    private UUID customerTransactionId;

    @Column(name = "customer_transaction_mapping_id")
    private UUID customerTransactionMappingId;

    @Column(name = "data")
    private byte[] data;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
