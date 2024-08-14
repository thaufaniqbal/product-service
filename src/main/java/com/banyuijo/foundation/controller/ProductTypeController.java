package com.banyuijo.foundation.controller;

import com.banyuijo.foundation.dto.base.ApiResponseDto;
import com.banyuijo.foundation.service.product.type.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-type")
public class ProductTypeController {
    private final ProductTypeService productTypeService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDto<Object>> getProductTypeCodeList() {
        return ApiResponseDto.toResponseEntity(HttpStatus.OK, productTypeService.getAllProductCode());
    }

}
