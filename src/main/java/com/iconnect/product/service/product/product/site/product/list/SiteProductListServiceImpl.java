package com.iconnect.product.service.product.product.site.product.list;

import com.iconnect.product.dto.product.site.product.SiteProductListOutput;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.gateway.product.site.SiteProductGateway;
import com.iconnect.product.service.product.product.type.validator.ProductTypeValidator;
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
