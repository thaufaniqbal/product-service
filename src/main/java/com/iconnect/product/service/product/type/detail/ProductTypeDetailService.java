package com.iconnect.product.service.product.type.detail;

import com.iconnect.product.dto.product.type.ProductTypeDetailOutput;

import java.util.UUID;

public interface ProductTypeDetailService {
    ProductTypeDetailOutput getProductTypeDetail(UUID productTypeId);
}
