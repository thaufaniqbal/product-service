package com.banyuijo.foundation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_address")
public class Address {

    @Id
    @Column(name = "address_Id")
    private UUID addressId;

    @Column(name = "address")
    private String address;

    @Column(name = "fax")
    private String fax;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

}
