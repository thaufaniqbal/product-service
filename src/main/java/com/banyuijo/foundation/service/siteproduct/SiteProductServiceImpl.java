package com.banyuijo.foundation.service.siteproduct;

import com.banyuijo.foundation.dto.product.SiteProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteProductServiceImpl implements SiteProductService {
    @Override
    public SiteProductDto getProductDetail() {
        return getDetail(null);
    }
    private SiteProductDto getDetail(UUID siteId){
        if (Objects.isNull(siteId)){
            SiteProductDto.SiteProductStatus status = new SiteProductDto().new SiteProductStatus(true, false, true, false);
            SiteProductDto siteProductDto = new SiteProductDto(
                    UUID.randomUUID(),
                    7.8,
                    7.5,
                    6.5,
                    status
            );
            return siteProductDto;
        }
        return null;
    }
}
