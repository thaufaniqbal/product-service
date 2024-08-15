package com.banyuijo.foundation.controller;

import com.banyuijo.foundation.dto.base.ApiResponseDto;
import com.banyuijo.foundation.dto.product.site.SiteProductCreateInput;
import com.banyuijo.foundation.dto.product.site.SiteProductEditInput;
import com.banyuijo.foundation.dto.product.site.structure.edit.SiteProductEditStructureInput;
import com.banyuijo.foundation.service.product.site.detail.SiteProductDetailService;
import com.banyuijo.foundation.service.product.site.create.SiteProductCreateService;
import com.banyuijo.foundation.service.product.site.edit.SiteProductEditService;
import com.banyuijo.foundation.service.product.site.list.SiteProductListService;
import com.banyuijo.foundation.service.product.site.structure.detail.SiteProductDetailStructureService;
import com.banyuijo.foundation.service.product.site.structure.edit.SiteProductEditStructureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/site-product")
public class SiteProductController {
    private final SiteProductDetailService siteProductDetailService;
    private final SiteProductCreateService siteProductCreateService;
    private final SiteProductEditService siteProductEditService;
    private final SiteProductListService siteProductListService;
    private final SiteProductDetailStructureService siteProductDetailStructureService;
    private final SiteProductEditStructureService siteProductEditStructureService;
    @GetMapping("/{siteProductId}")
    public ResponseEntity<ApiResponseDto<Object>> getSiteProductDetail(@PathVariable UUID siteProductId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.OK, siteProductDetailService.getSiteProductDetail(siteProductId));
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<Object>> createSiteProduct(@RequestBody SiteProductCreateInput request,
                                                            @RequestHeader("login-id") String loginId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.CREATED, siteProductCreateService.createSiteProduct(request, loginId));
    }
    @PutMapping("/{siteProductId}")
    public ResponseEntity<ApiResponseDto<Object>> editSiteProduct(@RequestBody SiteProductEditInput request,
                                                                @RequestHeader("login-id") String loginId,
                                                                @PathVariable UUID siteProductId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.ACCEPTED, siteProductEditService.editSiteProduct(request, loginId, siteProductId));
    }
    @GetMapping("/list")
    public ResponseEntity<ApiResponseDto<Object>> getSiteProductList() {
        return ApiResponseDto.toResponseEntity(HttpStatus.OK, siteProductListService.getSiteProductList());
    }
    @GetMapping("/list/{productTypeId}")
    public ResponseEntity<ApiResponseDto<Object>> getSiteProductListByProductType(@PathVariable UUID productTypeId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.OK, siteProductListService.getSiteProductListByProductType(productTypeId));
    }
    @GetMapping("/structure/{siteProductId}")
    public ResponseEntity<ApiResponseDto<Object>> getSiteProductStructureDetail(@PathVariable UUID siteProductId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.OK, siteProductDetailStructureService.getSiteProductStructureDetail(siteProductId));
    }
    @PutMapping("/structure/{siteProductId}")
    public ResponseEntity<ApiResponseDto<Object>> editSiteProductStructure(@RequestBody SiteProductEditStructureInput request,
                                                              @RequestHeader("login-id") String loginId,
                                                              @PathVariable UUID siteProductId) {
        return ApiResponseDto.toResponseEntity(HttpStatus.ACCEPTED, siteProductEditStructureService.editSiteProductStructure(request, loginId, siteProductId));
    }
}
