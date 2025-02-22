package com.iconnect.product.entity.transaction.device.ids;

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
public class DeviceTransactionMappingIds implements Serializable {
    @Id
    @Column(name = "device_transaction_mapping_id")
    private UUID deviceTransactionMappingId;

    @Id
    @Column(name = "company_id")
    private UUID companyId;

    @Id
    @Column(name = "customer_id")
    private UUID customerId;
}