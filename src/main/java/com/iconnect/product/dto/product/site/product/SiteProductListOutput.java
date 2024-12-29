package com.iconnect.product.dto.product.site.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductListOutput {

    private UUID siteProductId;

    private String siteProductName;

    private String siteProductCode;

}
