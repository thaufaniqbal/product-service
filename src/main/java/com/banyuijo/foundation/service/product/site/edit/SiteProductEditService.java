package com.banyuijo.foundation.service.product.site.edit;

import com.banyuijo.foundation.dto.product.site.SiteProductEditInput;

import java.util.UUID;

public interface SiteProductEditService {
    Object editProduct(SiteProductEditInput request, String loginId, UUID siteProductId);

}