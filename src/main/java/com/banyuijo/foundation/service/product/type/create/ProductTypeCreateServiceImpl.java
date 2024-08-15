package com.banyuijo.foundation.service.product.type.create;

import com.banyuijo.foundation.dto.product.type.ProductTypeCreateInput;
import com.banyuijo.foundation.service.product.type.validator.ProductTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTypeCreateServiceImpl implements ProductTypeCreateService {
    private final ProductTypeValidator validator;
    @Override
    public Object createProductType(ProductTypeCreateInput request, String loginId) {
        validator.validateRequest(request, null);
        return null;
    }
}
