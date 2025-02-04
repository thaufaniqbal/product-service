package com.iconnect.product.repository.product;

import com.iconnect.product.entity.product.ProductTemplateMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductTemplateMappingRepository extends JpaRepository <ProductTemplateMapping, UUID> {
    List<ProductTemplateMapping> findAllByProductTemplateId (UUID productTemplateId);

    ProductTemplateMapping findBySettingDataCodeIgnoreCase(String settingDataCode);
}
