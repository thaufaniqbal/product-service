package com.iconnect.product.repository.transaction.device;

import com.iconnect.product.entity.transaction.device.DeviceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceTransactionRepository extends JpaRepository <DeviceTransaction, UUID> {
    Optional<DeviceTransaction> findTopByDeviceTransactionMappingIdOrderByCreatedDateDesc (UUID deviceTransactionMappingId);
}
