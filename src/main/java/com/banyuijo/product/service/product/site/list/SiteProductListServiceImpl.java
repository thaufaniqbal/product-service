package com.banyuijo.product.service.product.site.list;

import com.banyuijo.product.dto.product.site.SiteProductListOutput;
import com.banyuijo.product.entity.SiteProduct;
import com.banyuijo.product.enums.BooleanStatus;
import com.banyuijo.product.repository.SiteProductRepository;
import com.banyuijo.product.service.product.type.validator.ProductTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteProductListServiceImpl implements SiteProductListService {
    private final SiteProductRepository siteProductRepository;
    private final ProductTypeValidator productTypeValidator;
    @Override
    public List<SiteProductListOutput> getSiteProductList() {
        return siteProductRepository.findAllByDeleteStatus(BooleanStatus.NO.getCode()).stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    @Override
    public List<SiteProductListOutput> getSiteProductListByProductType(UUID productTypeId) {
        productTypeValidator.validateProductTypeId(productTypeId);
        return siteProductRepository.findAllByProductTypeIdAndDeleteStatus(productTypeId, BooleanStatus.NO.getCode()).stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }
    private SiteProductListOutput toOutput(SiteProduct siteProduct) {
        SiteProductListOutput output = new SiteProductListOutput();
        output.setSiteProductId(siteProduct.getSiteProductId());
        output.setSiteProductName(siteProduct.getSiteProductName());
        output.setSiteProductCode(siteProduct.getSiteProductCode());
        return output;
    }
}
