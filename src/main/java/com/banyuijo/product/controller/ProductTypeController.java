package com.banyuijo.product.controller;

import com.banyuijo.product.dto.base.ApiResponseDTO;
import com.banyuijo.product.dto.product.type.ProductTypeCreateInput;
import com.banyuijo.product.dto.product.type.ProductTypeEditInput;
import com.banyuijo.product.dto.product.type.ProductTypeSearchInput;
import com.banyuijo.product.service.product.type.create.ProductTypeCreateService;
import com.banyuijo.product.service.product.type.detail.ProductTypeDetailService;
import com.banyuijo.product.service.product.type.edit.ProductTypeEditService;
import com.banyuijo.product.service.product.type.list.ProductTypeListService;
import com.banyuijo.product.service.product.type.search.ProductTypeSearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-type")
public class ProductTypeController {
    private final ProductTypeSearchService productTypeSearchService;
    private final ProductTypeListService productTypeListService;
    private final ProductTypeDetailService productTypeDetailService;
    private final ProductTypeCreateService productTypeCreateService;
    private final ProductTypeEditService productTypeEditService;
    @GetMapping("/")
    public ResponseEntity<ApiResponseDTO<Object>> searchProductType(@RequestBody ProductTypeSearchInput input) {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, productTypeSearchService.searchProductType(input));
    }
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDTO<Object>> getAllProductCode() {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, productTypeListService.getAllProductCode());
    }
    @GetMapping("/{productTypeId}")
    public ResponseEntity<ApiResponseDTO<Object>> getProductTypeDetail(@PathVariable UUID productTypeId) {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, productTypeDetailService.getProductTypeDetail(productTypeId));
    }
    @PostMapping("/")
    public ResponseEntity<ApiResponseDTO<Object>> createProductType(@RequestBody ProductTypeCreateInput request,
                                                                    @RequestHeader("login-id") String loginId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.CREATED, productTypeCreateService.createProductType(request, loginId));
    }
    @PutMapping("/{productTypeId}")
    public ResponseEntity<ApiResponseDTO<Object>> editProductType(@RequestBody ProductTypeEditInput request,
                                                                  @RequestHeader("login-id") String loginId,
                                                                  @PathVariable UUID productTypeId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.ACCEPTED, productTypeEditService.editProductType(request, loginId, productTypeId));
    }
}
