package com.banyuijo.product.service.product.type.search;

import com.banyuijo.product.dto.base.ResponsePageDTO;
import com.banyuijo.product.dto.product.type.ProductTypeSearchInput;
import com.banyuijo.product.dto.product.type.ProductTypeSearchOutput;
import com.banyuijo.product.gateway.product.type.ProductTypeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTypeSearchServiceImpl implements ProductTypeSearchService {
    private final ProductTypeGateway productTypeGateway;
    @Override
    public ResponsePageDTO searchProductType(ProductTypeSearchInput input) {
        Page<ProductTypeSearchOutput> outputPage = productTypeGateway.
                getSearchSiteProduct(input.getProductTypeCode(),
                        input.getProductTypeName(),
                        PageRequest.of(input.getOffset(), input.getSize())
                );
        ResponsePageDTO output = new ResponsePageDTO<>();
        output.setList(outputPage.getContent());
        output.setPage(outputPage.getNumber());
        output.setResultPerPage(outputPage.getSize());
        output.setTotalResult(outputPage.getTotalElements());
        return output;
    }
}
