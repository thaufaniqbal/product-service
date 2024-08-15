package com.banyuijo.foundation.dto.product.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductTypeCreateInput {
    private String productTypeCode;
    private String productTypeName;
}
