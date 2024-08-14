package com.banyuijo.foundation.service.product.type;

import com.banyuijo.foundation.entity.ProductType;
import com.banyuijo.foundation.enums.HttpStatusCode;
import com.banyuijo.foundation.exception.HttpStatusException;
import com.banyuijo.foundation.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeRepository productTypeRepository;
    @Override
    public List<String> getAllProductCode() {
        List<ProductType> productTypes = productTypeRepository.findAll();
        if (productTypes.isEmpty()){
            throw new HttpStatusException(HttpStatusCode.DATA_NOT_FOUND);
        }
        return productTypes.stream()
                .map(ProductType::getProductTypeCode)
                .collect(Collectors.toList());
    }
}
