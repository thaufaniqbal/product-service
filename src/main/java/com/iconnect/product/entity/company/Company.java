package com.iconnect.product.entity.company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "mst_company")
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @Column(name = "company_id", columnDefinition = "char(36)")
    private UUID companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address_id")
    private UUID addressId;

    @Column(name = "contact_id")
    private UUID contactId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;

    @Column(name = "delete_status")
    private Integer deleteStatus;
}
