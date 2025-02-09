package com.iconnect.product.entity.foundation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "mst_state")
public class State {
    @Id
    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "country_code")
    private String countryCode;
}
