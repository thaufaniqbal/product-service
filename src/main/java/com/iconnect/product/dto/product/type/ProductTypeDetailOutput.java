package com.iconnect.product.dto.product.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductTypeDetailOutput {

    private UUID productTypeId;

    private String productTypeName;

    private String productTypeCode;

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastUpdatedBy;

    private LocalDateTime lastUpdatedDate;

    private Integer deleteStatus;
}
