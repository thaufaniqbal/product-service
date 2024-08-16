package com.banyuijo.product.dto.product.site;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductEditInput {

    private String siteProductName;

    private String productTypeCode;

}
