package com.banyuijo.foundation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "mst_city")
public class City {
    @Id
    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "state_code")
    private String stateCode;
}
