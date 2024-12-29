package com.banyuijo.product.service.product.site.product.list;

import com.banyuijo.product.dto.product.site.product.SiteProductListOutput;
import com.banyuijo.product.enums.BooleanStatus;
import com.banyuijo.product.gateway.product.site.SiteProductGateway;
import com.banyuijo.product.service.product.type.validator.ProductTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteProductListServiceImpl implements SiteProductListService {
    private final ProductTypeValidator productTypeValidator;
    private final SiteProductGateway siteProductGateway;
    @Override
    public List<SiteProductListOutput> getSiteProductList() {
        return siteProductGateway.getListByDeleteStatusAndProductTypeId(BooleanStatus.NO.getCode(), null);
    }

    @Override
    public List<SiteProductListOutput> getSiteProductListByProductType(UUID productTypeId) {
        productTypeValidator.validateProductTypeId(productTypeId);
        return siteProductGateway.getListByDeleteStatusAndProductTypeId(BooleanStatus.NO.getCode(), productTypeId);
    }
}
