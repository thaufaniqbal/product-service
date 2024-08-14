package com.banyuijo.foundation.service.product.site.create;

import com.banyuijo.foundation.dto.product.SiteProductCreateRequest;
import com.banyuijo.foundation.entity.SiteProduct;
import com.banyuijo.foundation.enums.BooleanStatus;
import com.banyuijo.foundation.enums.HttpStatusCode;
import com.banyuijo.foundation.exception.HttpStatusException;
import com.banyuijo.foundation.repository.SiteProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteProductCreateServiceImpl implements SiteProductCreateService {

    private final SiteProductRepository siteProductRepository;

    @Override
    public Object createProduct(SiteProductCreateRequest request, String loginId) {
        if (siteProductRepository.existsBySiteProductCodeIgnoreCase(request.getSiteProductCode())){
            throw new HttpStatusException(HttpStatusCode.DATA_ALREADY_EXIST, request.getSiteProductCode());
        }
        SiteProduct siteProduct = build(request, loginId);
        saveProduct(siteProduct);
        return request;
    }

    @Transactional
    private void saveProduct(SiteProduct siteProduct){
        siteProductRepository.save(siteProduct);
    }

    private SiteProduct build (SiteProductCreateRequest request, String loginId){
        SiteProduct siteProduct = new SiteProduct();
        siteProduct.setSiteProductId(UUID.randomUUID());
        siteProduct.setSiteProductName(request.getSiteProductName());
        siteProduct.setSiteProductCode(request.getSiteProductCode());
        siteProduct.setProductTypeId(request.getProductTypeId());
        siteProduct.setCreatedBy(loginId);
        siteProduct.setCreatedDate(LocalDateTime.now());
        siteProduct.setLastUpdatedBy(loginId);
        siteProduct.setLastUpdatedDate(LocalDateTime.now());
        siteProduct.setDeleteStatus(BooleanStatus.NO.getCode());
        return siteProduct;
    }

}
