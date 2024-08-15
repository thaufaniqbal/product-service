package com.banyuijo.foundation.service.product.type.list;

import com.banyuijo.foundation.dto.product.type.ProductTypeListOutput;
import com.banyuijo.foundation.entity.ProductType;
import com.banyuijo.foundation.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductTypeListServiceImpl implements ProductTypeListService {
    private final ProductTypeRepository productTypeRepository;
    @Override
    public List<ProductTypeListOutput> getAllProductCode() {
        List<ProductType> productTypes = productTypeRepository.findAll();
        return productTypes.stream()
                .map(pt -> new ProductTypeListOutput(pt.getProductTypeId(), pt.getProductTypeCode()))
                .collect(Collectors.toList());
    }
}
