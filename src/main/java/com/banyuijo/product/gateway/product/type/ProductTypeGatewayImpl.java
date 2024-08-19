package com.banyuijo.product.gateway.product.type;

import com.banyuijo.product.dto.product.type.ProductTypeListOutput;
import com.banyuijo.product.dto.product.type.ProductTypeSearchOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
public class ProductTypeGatewayImpl implements ProductTypeGateway {
    @Override
    public List<ProductTypeListOutput> findAllByDeleteStatus(int deleteStatus) {
        return null;
    }

    @Override
    public Page<ProductTypeSearchOutput> getSearchSiteProduct(String productCode, String productName, Pageable pageable) {
        return null;
    }
}
