package com.banyuijo.foundation.controller;

import com.banyuijo.foundation.dto.base.ApiResponseDto;
import com.banyuijo.foundation.dto.product.site.SiteProductInput;
import com.banyuijo.foundation.service.product.site.SiteProductService;
import com.banyuijo.foundation.service.product.site.create.SiteProductCreateService;
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
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponseDto<Object>> getDetail(@PathVariable UUID productId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.OK, siteProductService.getProductDetail(productId));
    }
    @GetMapping("/create")
    public ResponseEntity<ApiResponseDto<Object>> getDetail(@RequestBody SiteProductInput request,
                                                            @RequestHeader("login-id") String loginId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.CREATED, siteProductCreateService.createProduct(request, loginId));
    }
}
