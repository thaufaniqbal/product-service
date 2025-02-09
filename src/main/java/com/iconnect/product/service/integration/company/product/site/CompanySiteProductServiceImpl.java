package com.iconnect.product.service.integration.company.product.site;

import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CompanySiteProductServiceImpl implements CompanySiteProductService {
    @Override
    public Object createProductByCompany(UUID userId, SiteProductCreateInput input) {
        return null;
    }
}
