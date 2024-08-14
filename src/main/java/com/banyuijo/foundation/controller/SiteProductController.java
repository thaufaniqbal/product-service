package com.banyuijo.foundation.controller;

import com.banyuijo.foundation.dto.base.ApiResponseDto;
import com.banyuijo.foundation.dto.product.site.SiteProductCreateInput;
import com.banyuijo.foundation.dto.product.site.SiteProductEditInput;
import com.banyuijo.foundation.service.product.site.SiteProductService;
import com.banyuijo.foundation.service.product.site.create.SiteProductCreateService;
import com.banyuijo.foundation.service.product.site.edit.SiteProductEditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/site-product")
public class SiteProductController {
    private final SiteProductService siteProductService;
    private final SiteProductCreateService siteProductCreateService;
    private final SiteProductEditService siteProductEditService;
    @GetMapping("/{siteProductId}")
    public ResponseEntity<ApiResponseDto<Object>> getDetail(@PathVariable UUID siteProductId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.OK, siteProductService.getProductDetail(siteProductId));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<Object>> createProduct(@RequestBody SiteProductCreateInput request,
                                                            @RequestHeader("login-id") String loginId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.CREATED, siteProductCreateService.createProduct(request, loginId));
    }
    @PutMapping("/{siteProductId}")
    public ResponseEntity<ApiResponseDto<Object>> editProduct(@RequestBody SiteProductEditInput request,
                                                                @RequestHeader("login-id") String loginId,
                                                                @PathVariable UUID siteProductId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.ACCEPTED, siteProductEditService.editProduct(request, loginId, siteProductId));
    }
}
