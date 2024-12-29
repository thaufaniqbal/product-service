package com.banyuijo.product.service.product.site.product.search;

import com.banyuijo.product.dto.base.ResponsePageDTO;
import com.banyuijo.product.dto.product.site.product.SiteProductSearchInput;
import com.banyuijo.product.dto.product.site.product.SiteProductSearchOutput;
import com.banyuijo.product.gateway.product.site.SiteProductGateway;
import com.banyuijo.product.service.product.type.validator.ProductTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SiteProductSearchServiceImpl implements SiteProductSearchService {
    private final SiteProductGateway siteProductGateway;
    private final ProductTypeValidator productTypeValidator;
    @Override
    public ResponsePageDTO searchSiteProduct(SiteProductSearchInput input) {
        if (Objects.nonNull(input.getProductTypeId()) && !input.getProductTypeId().toString().equals("")){
            productTypeValidator.validateProductTypeId(input.getProductTypeId());
        }
        Page<SiteProductSearchOutput> pageSiteProduct = siteProductGateway.
                getSearchSiteProduct(input.getProductCode(),
                        input.getProductName(),
                        input.getProductTypeId(),
                        PageRequest.of(input.getOffset(), input.getSize())
                );
        ResponsePageDTO output = new ResponsePageDTO<>();
        output.setList(pageSiteProduct.getContent());
        output.setPage(pageSiteProduct.getNumber());
        output.setResultPerPage(pageSiteProduct.getSize());
        output.setTotalResult(pageSiteProduct.getTotalElements());
        return output;
    }
}
