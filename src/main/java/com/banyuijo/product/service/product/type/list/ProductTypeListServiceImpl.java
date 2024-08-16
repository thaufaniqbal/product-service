package com.banyuijo.product.service.product.type.list;

import com.banyuijo.product.dto.product.type.ProductTypeListOutput;
import com.banyuijo.product.entity.ProductType;
import com.banyuijo.product.enums.BooleanStatus;
import com.banyuijo.product.repository.ProductTypeRepository;
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
        List<ProductType> productTypes = productTypeRepository.findAllByDeleteStatus(BooleanStatus.NO.getCode());
        return productTypes.stream()
                .map(pt -> new ProductTypeListOutput(pt.getProductTypeId(), pt.getProductTypeCode()))
                .collect(Collectors.toList());
    }
}
