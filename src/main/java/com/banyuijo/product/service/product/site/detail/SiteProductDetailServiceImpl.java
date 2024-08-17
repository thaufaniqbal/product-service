package com.banyuijo.product.service.product.site.detail;

import com.banyuijo.product.dto.product.site.SiteProductDetailOutput;
import com.banyuijo.product.entity.ProductType;
import com.banyuijo.product.entity.SiteProduct;
import com.banyuijo.product.enums.HttpStatusCode;
import com.banyuijo.product.exception.HttpStatusException;
import com.banyuijo.product.repository.ProductTypeRepository;
import com.banyuijo.product.repository.SiteProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteProductDetailServiceImpl implements SiteProductDetailService {
    private final SiteProductRepository siteProductRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ObjectMapper objectMapper;
    @Override
    public SiteProductDetailOutput getSiteProductDetail(UUID productId) {
        SiteProduct siteProduct = siteProductRepository.findById(productId)
                .orElseThrow(()->new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND));
        ProductType productType = productTypeRepository.findByProductTypeId(siteProduct.getProductTypeId());

        SiteProductDetailOutput output = objectMapper.convertValue(siteProduct, SiteProductDetailOutput.class);
        output.setProductTypeCode(productType.getProductTypeCode());
        return output;
    }

}
