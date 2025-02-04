package com.iconnect.product.service.product.product.site.product.detail;

import com.iconnect.product.dto.product.site.product.SiteProductDetailOutput;
import com.iconnect.product.dto.product.site.structure.detail.SiteProductDetailStructureOutput;
import com.iconnect.product.entity.ProductType;
import com.iconnect.product.entity.SiteProduct;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.ProductTypeRepository;
import com.iconnect.product.repository.SiteProductRepository;
import com.iconnect.product.service.product.product.site.structure.detail.SiteProductDetailStructureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteProductDetailServiceImpl implements SiteProductDetailService {
    private final SiteProductRepository siteProductRepository;
    private final ProductTypeRepository productTypeRepository;
    private final SiteProductDetailStructureService siteProductDetailStructureService;
    private final ObjectMapper objectMapper;
    @Override
    public SiteProductDetailOutput getSiteProductDetail(UUID productId) throws JsonProcessingException {
        SiteProduct siteProduct = siteProductRepository.findById(productId)
                .orElseThrow(()->new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND));
        ProductType productType = productTypeRepository.findByProductTypeId(siteProduct.getProductTypeId());

        SiteProductDetailOutput output = objectMapper.convertValue(siteProduct, SiteProductDetailOutput.class);
        output.setProductTypeCode(productType.getProductTypeCode());
        SiteProductDetailStructureOutput structureOutput = siteProductDetailStructureService.getSiteProductStructureDetail(productId);
        output.setStructureOutput(structureOutput);
        return output;
    }

}
