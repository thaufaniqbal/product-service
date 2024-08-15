package com.banyuijo.foundation.service.product.site.structure.builder;

import java.util.UUID;

public interface SiteProductStructureBuilder {
    void init(UUID sideProductId, UUID productTypeId, String productName);
}
