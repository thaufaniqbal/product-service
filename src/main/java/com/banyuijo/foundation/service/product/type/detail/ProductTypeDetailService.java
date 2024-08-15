package com.banyuijo.foundation.service.product.type.detail;

import com.banyuijo.foundation.dto.product.type.ProductTypeDetailOutput;

import java.util.UUID;

public interface ProductTypeDetailService {
    ProductTypeDetailOutput getProductTypeDetail(UUID productTypeId);
}
