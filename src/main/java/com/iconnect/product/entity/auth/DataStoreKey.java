package com.iconnect.product.entity.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "mst_data_store_key")
@AllArgsConstructor
@NoArgsConstructor
public class DataStoreKey {
    @Id
    @Column(name = "store_key")
    private String storeKey;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "active_status")
    private Integer activeStatus;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
