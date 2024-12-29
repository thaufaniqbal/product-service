package com.banyuijo.product.repository;

import com.banyuijo.product.entity.ProductTemplateMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductTemplateMappingRepository extends JpaRepository <ProductTemplateMapping, UUID> {
    List<ProductTemplateMapping> findAllByProductTemplateId (UUID productTemplateId);

    ProductTemplateMapping findBySettingDataCodeIgnoreCase(String settingDataCode);
}
