package com.banyuijo.product.dto.product.site;

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
public class SiteProductSearchOutput {
    private UUID siteProductId;

    private String siteProductName;

    private String siteProductCode;

    private UUID productTypeId;

    private String productTypeName;

    private LocalDateTime lastUpdatedDate;

}
