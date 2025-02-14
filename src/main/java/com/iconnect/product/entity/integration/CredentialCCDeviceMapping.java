package com.iconnect.product.entity.integration;

import com.iconnect.product.entity.integration.id.CompanyCustomerIds;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_ccc_device_mapping")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CompanyCustomerIds.class)
public class CredentialCCDeviceMapping {
    @Id
    @Column(name = "company_id")
    private UUID companyId;

    @Id
    @Column(name = "customer_id")
    private UUID customerId;

    @Column(name = "credential_key")
    private String credentialKey;
}
