package com.iconnect.product.entity.transaction.device;

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
@Table(name = "trx_device_transaction")
@AllArgsConstructor
@NoArgsConstructor
public class DeviceTransaction {
    @Id
    @Column(name = "device_transaction_id")
    private UUID deviceTransactionId;

    @Column(name = "device_transaction_mapping_id")
    private UUID deviceTransactionMappingId;

    @Column(name = "data")
    @Lob
    private byte[] data;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
