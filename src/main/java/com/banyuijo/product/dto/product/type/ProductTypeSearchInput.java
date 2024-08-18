package com.banyuijo.product.dto.product.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductTypeSearchInput {

    private String productTypeCode;

    private String productTypeName;

    private int size;

    private int offset;

}
