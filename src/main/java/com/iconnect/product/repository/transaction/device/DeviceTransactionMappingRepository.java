package com.iconnect.product.repository.transaction.device;

import com.iconnect.product.entity.transaction.device.DeviceTransactionMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceTransactionMappingRepository extends JpaRepository<DeviceTransactionMapping, UUID> {
    Optional< DeviceTransactionMapping> findByCustomerIdAndCompanyId (UUID customerId, UUID companyId);
}
