package com.iconnect.product.service.product.product.type.list;

import com.iconnect.product.dto.product.type.ProductTypeListOutput;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.gateway.product.type.ProductTypeGateway;
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
