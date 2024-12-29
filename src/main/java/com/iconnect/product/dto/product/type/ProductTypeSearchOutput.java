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
public class ProductTypeSearchOutput {

    private UUID productTypeId;

    private String productTypeCode;

    private String productTypeName;

    private LocalDateTime lastUpdatedDate;

}
