package com.banyuijo.foundation.service.product.type.create;

import com.banyuijo.foundation.dto.product.type.ProductTypeCreateInput;

public interface ProductTypeCreateService {
    Object createProductType(ProductTypeCreateInput request, String loginId);
}
