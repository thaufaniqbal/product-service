package com.banyuijo.product.repository;

import com.banyuijo.product.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductTypeRepository extends JpaRepository <ProductType, UUID> {
    Boolean existsByProductTypeCode(String productTypeCode);
    Boolean existsByProductTypeId(UUID productTypeId);
    ProductType findByProductTypeCode(String productTypeCode);
    ProductType findByProductTypeId(UUID productTypeId);
    List<ProductType> findAllByDeleteStatus(Integer deleteStatus);
}
