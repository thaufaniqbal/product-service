package com.banyuijo.product.gateway.product.type;

import com.banyuijo.product.dto.product.type.ProductTypeListOutput;
import com.banyuijo.product.dto.product.type.ProductTypeSearchOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductTypeGateway {
    List<ProductTypeListOutput> findAllByDeleteStatus (int deleteStatus);
    Page<ProductTypeSearchOutput> getSearchSiteProduct (String productCode, String productName, Pageable pageable);

}
