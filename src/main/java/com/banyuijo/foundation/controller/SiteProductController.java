package com.banyuijo.foundation.controller;

import com.banyuijo.foundation.dto.base.ApiResponseDto;
import com.banyuijo.foundation.service.siteproduct.SiteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/site-product")
public class SiteProductController {
    private final SiteProductService siteProductService;
    @GetMapping("/id")
    public ResponseEntity<ApiResponseDto<Object>> getDetail() {
        return ApiResponseDto.toResponseEntity(HttpStatus.OK, siteProductService.getProductDetail());
    }
}
