package com.iconnect.product.entity.auth;

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
@Table(name = "mst_entity_user_company")
@AllArgsConstructor
@NoArgsConstructor
public class EntityUserCompany {
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "company_id")
    private UUID companyId;
}
