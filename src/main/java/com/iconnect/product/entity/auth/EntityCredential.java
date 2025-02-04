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
@Table(name = "mst_entity_credential")
@AllArgsConstructor
@NoArgsConstructor
public class EntityCredential {
    @Id
    @Column(name = "user_id", columnDefinition = "char(36)")
    private UUID userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;
}
