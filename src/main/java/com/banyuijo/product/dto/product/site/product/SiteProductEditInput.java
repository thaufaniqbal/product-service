package com.banyuijo.product.dto.product.site.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SiteProductEditInput {

    private String siteProductName;

    private UUID productTypeId;

}
