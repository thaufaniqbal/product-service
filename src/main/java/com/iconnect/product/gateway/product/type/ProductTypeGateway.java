package com.iconnect.product.gateway.product.type;

import com.iconnect.product.dto.product.type.ProductTypeListOutput;
import com.iconnect.product.dto.product.type.ProductTypeSearchOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductTypeGateway {
    List<ProductTypeListOutput> findAllByDeleteStatus (int deleteStatus);
    Page<ProductTypeSearchOutput> getSearchSiteProduct (String productCode, String productName, Pageable pageable);

}
