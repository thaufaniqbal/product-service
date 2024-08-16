package com.banyuijo.product.service.product.type.detail;

import com.banyuijo.product.dto.product.type.ProductTypeDetailOutput;

import java.util.UUID;

public interface ProductTypeDetailService {
    ProductTypeDetailOutput getProductTypeDetail(UUID productTypeId);
}
