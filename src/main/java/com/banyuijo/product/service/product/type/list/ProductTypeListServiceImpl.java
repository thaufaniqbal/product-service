package com.banyuijo.product.service.product.type.list;

import com.banyuijo.product.dto.product.type.ProductTypeListOutput;
import com.banyuijo.product.enums.BooleanStatus;
import com.banyuijo.product.gateway.product.type.ProductTypeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeListServiceImpl implements ProductTypeListService {
    private final ProductTypeGateway productTypeGateway;
    @Override
    public List<ProductTypeListOutput> getAllProductCode() {
        return productTypeGateway.findAllByDeleteStatus(BooleanStatus.NO.getCode());
    }
}
