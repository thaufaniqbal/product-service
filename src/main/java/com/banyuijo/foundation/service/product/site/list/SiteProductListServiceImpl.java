package com.banyuijo.foundation.service.product.site.list;

import com.banyuijo.foundation.dto.product.site.SiteProductListOutput;
import com.banyuijo.foundation.entity.SiteProduct;
import com.banyuijo.foundation.enums.HttpStatusCode;
import com.banyuijo.foundation.exception.HttpStatusException;
import com.banyuijo.foundation.repository.ProductTypeRepository;
import com.banyuijo.foundation.repository.SiteProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteProductListServiceImpl implements SiteProductListService {
    private final SiteProductRepository siteProductRepository;
    private final ProductTypeRepository productTypeRepository;
    @Override
    public List<SiteProductListOutput> getSiteProductList() {
        return siteProductRepository.findAll().stream()
                .map(this::toOutput)
                .collect(Collectors.toList());
    }

    @Override
    public List<SiteProductListOutput> getSiteProductListByProductType(UUID productTypeId) {
        if (!productTypeRepository.existsById(productTypeId)){
            throw new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND, "product type");
        }
        return siteProductRepository.findAllByProductTypeId(productTypeId).stream()
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
