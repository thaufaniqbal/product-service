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
public class SiteProductSearchInput {

    private String productName;

    private String productCode;

    private UUID productTypeId;

    private int size;

    private int offset;

}
