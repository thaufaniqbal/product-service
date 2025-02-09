package com.iconnect.product.repository.auth;

import com.iconnect.product.entity.auth.EntityUserCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntityUserCompanyRepository extends JpaRepository<EntityUserCompany, UUID> {
}
