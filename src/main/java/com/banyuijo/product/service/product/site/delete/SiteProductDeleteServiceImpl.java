package com.banyuijo.product.service.product.site.delete;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class SiteProductDeleteServiceImpl implements SiteProductDeleteService {
    @Override
    public Object deleteSiteProduct(UUID siteProductId, String loginId) throws JsonProcessingException {
        return null;
    }
}
