package com.iconnect.product.dto.product.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductTypeListOutput {
    private UUID productTypeId;
    private String productTypeName;
    private String productTypeCode;
}
