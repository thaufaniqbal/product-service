package com.iconnect.product.entity.foundation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_credential_key_company")
@AllArgsConstructor
@NoArgsConstructor
public class CredentialKeyCompany {
    @Id
    @Column(name = "company_id")
    private UUID companyId;

    @Column(name = "key_total")
    private int keyTotal;
}
