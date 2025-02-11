package com.iconnect.product.controller;

import com.iconnect.product.dto.ApiResponseDTO;
import com.iconnect.product.dto.product.type.ProductTypeCreateInput;
import com.iconnect.product.dto.product.type.ProductTypeEditInput;
import com.iconnect.product.dto.product.type.ProductTypeSearchInput;
import com.iconnect.product.service.product.product.type.create.ProductTypeCreateService;
import com.iconnect.product.service.product.product.type.delete.ProductTypeDeleteService;
import com.iconnect.product.service.product.product.type.detail.ProductTypeDetailService;
import com.iconnect.product.service.product.product.type.edit.ProductTypeEditService;
import com.iconnect.product.service.product.product.type.list.ProductTypeListService;
import com.iconnect.product.service.product.product.type.search.ProductTypeSearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product-type")
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
public class ProductTypeController {
    private final ProductTypeSearchService productTypeSearchService;
    private final ProductTypeListService productTypeListService;
    private final ProductTypeDetailService productTypeDetailService;
    private final ProductTypeCreateService productTypeCreateService;
    private final ProductTypeEditService productTypeEditService;
    private final ProductTypeDeleteService productTypeDeleteService;
    @GetMapping("/")
    public ResponseEntity<ApiResponseDTO<Object>> searchProductType(
            @RequestParam(required = false) String productTypeCode,
            @RequestParam(required = false) String productTypeName,
            @RequestParam(required = false) int size,
            @RequestParam(required = false) int offset) {

        ProductTypeSearchInput input = new ProductTypeSearchInput();
        input.setProductTypeCode(productTypeCode);
        input.setProductTypeName(productTypeName);
        input.setSize(size);
        input.setOffset(offset);

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
    @DeleteMapping("/{productTypeId}")
    public ResponseEntity<ApiResponseDTO<Object>> deleteProductType(@RequestHeader("login-id") String loginId,
                                                                  @PathVariable UUID productTypeId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.ACCEPTED, productTypeDeleteService.deleteProductType(productTypeId, loginId));
    }
}
