package com.iconnect.product.repository.company;

import com.iconnect.product.entity.foundation.CredentialKeyCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CredentialKeyCompanyRepository extends JpaRepository<CredentialKeyCompany, UUID> {
}
