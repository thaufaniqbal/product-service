package com.banyuijo.foundation.controller;

import com.banyuijo.foundation.dto.base.ApiResponseDto;
import com.banyuijo.foundation.dto.product.type.ProductTypeCreateInput;
import com.banyuijo.foundation.dto.product.type.ProductTypeEditInput;
import com.banyuijo.foundation.service.product.type.create.ProductTypeCreateService;
import com.banyuijo.foundation.service.product.type.detail.ProductTypeDetailService;
import com.banyuijo.foundation.service.product.type.edit.ProductTypeEditService;
import com.banyuijo.foundation.service.product.type.list.ProductTypeListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-type")
public class ProductTypeController {
    private final ProductTypeListService productTypeListService;
    private final ProductTypeDetailService productTypeDetailService;
    private final ProductTypeCreateService productTypeCreateService;
    private final ProductTypeEditService productTypeEditService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDto<Object>> getProductTypeCodeList() {
        return ApiResponseDto.toResponseEntity(HttpStatus.OK, productTypeListService.getAllProductCode());
    }
    @GetMapping("/{productTypeId}")
    public ResponseEntity<ApiResponseDto<Object>> getProductTypeDetail(@PathVariable UUID productTypeId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.OK, productTypeDetailService.getProductTypeDetail(productTypeId));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<Object>> createProductType(@RequestBody ProductTypeCreateInput request,
                                                                    @RequestHeader("login-id") String loginId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.CREATED, productTypeCreateService.createProductType(request, loginId));
    }
    @PutMapping("/{productTypeId}")
    public ResponseEntity<ApiResponseDto<Object>> editProductType(@RequestBody ProductTypeEditInput request,
                                                                  @RequestHeader("login-id") String loginId,
                                                                  @PathVariable UUID productTypeId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.ACCEPTED, productTypeEditService.editProductType(request, loginId, productTypeId));
    }
}
