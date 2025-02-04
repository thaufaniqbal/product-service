package com.iconnect.product.repository.auth;

import com.iconnect.product.entity.auth.EntityCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntityCredentialRepository extends JpaRepository<EntityCredential, UUID> {
}
