package com.banyuijo.foundation.service.product.site;

import com.banyuijo.foundation.dto.product.site.SiteProductDto;
import com.banyuijo.foundation.entity.SiteProduct;
import com.banyuijo.foundation.enums.HttpStatusCode;
import com.banyuijo.foundation.exception.HttpStatusException;
import com.banyuijo.foundation.repository.SiteProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteProductServiceImpl implements SiteProductService {
    private final SiteProductRepository siteProductRepository;
    private final ObjectMapper objectMapper;
    @Override
    public SiteProductDto getProductDetail(UUID productId) {
        SiteProduct siteProduct = siteProductRepository.findById(productId)
                .orElseThrow(()->new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND));
        return objectMapper.convertValue(siteProduct, SiteProductDto.class);
    }

}
