package com.iconnect.product.dto.product.site.product;

import com.iconnect.product.dto.product.site.structure.detail.SiteProductDetailStructureOutput;
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
public class SiteProductDetailOutput {
    private UUID siteProductId;

    private String siteProductName;

    private String siteProductCode;

    private UUID productTypeId;

    private String productTypeCode;

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastUpdatedBy;

    private LocalDateTime lastUpdatedDate;

    private Integer deleteStatus;

    private SiteProductDetailStructureOutput structureOutput;
}
