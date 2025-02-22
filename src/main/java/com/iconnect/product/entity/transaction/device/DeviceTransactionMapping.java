package com.iconnect.product.entity.transaction.device;

import com.iconnect.product.entity.transaction.device.ids.DeviceTransactionMappingIds;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "trx_device_transaction_mapping")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(DeviceTransactionMappingIds.class)
public class DeviceTransactionMapping {
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
