package com.iconnect.product.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class IntCompanyCustomerProductMappingOutput {
    private UUID customerId;
    private UUID siteProductId;
    private String productName;
    private String productType;
}
