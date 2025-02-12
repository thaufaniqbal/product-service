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
@Table(name = "trx_customer_site_product")
@AllArgsConstructor
@NoArgsConstructor
public class TrxCustomerSiteProduct {
    @Id
    @Column(name = "trx_id")
    private UUID trxId;

    @Column(name = "customer_mapping_id")
    private UUID customerMappingId;

    @Column(name = "data")
    private byte[] data;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
