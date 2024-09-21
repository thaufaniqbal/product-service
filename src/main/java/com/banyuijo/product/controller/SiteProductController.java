package com.banyuijo.product.controller;

import com.banyuijo.product.dto.base.ApiResponseDTO;
import com.banyuijo.product.dto.product.site.SiteProductCreateInput;
import com.banyuijo.product.dto.product.site.SiteProductEditInput;
import com.banyuijo.product.dto.product.site.SiteProductSearchInput;
import com.banyuijo.product.dto.product.site.structure.edit.SiteProductEditStructureInput;
import com.banyuijo.product.service.product.site.create.SiteProductCreateService;
import com.banyuijo.product.service.product.site.delete.SiteProductDeleteService;
import com.banyuijo.product.service.product.site.detail.SiteProductDetailService;
import com.banyuijo.product.service.product.site.edit.SiteProductEditService;
import com.banyuijo.product.service.product.site.list.SiteProductListService;
import com.banyuijo.product.service.product.site.search.SiteProductSearchService;
import com.banyuijo.product.service.product.site.structure.detail.SiteProductDetailStructureService;
import com.banyuijo.product.service.product.site.structure.edit.SiteProductEditStructureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/site-product")
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
public class SiteProductController {
    private final SiteProductSearchService siteProductSearchService;
    private final SiteProductDetailService siteProductDetailService;
    private final SiteProductCreateService siteProductCreateService;
    private final SiteProductEditService siteProductEditService;
    private final SiteProductDeleteService siteProductDeleteService;
    private final SiteProductListService siteProductListService;
    private final SiteProductDetailStructureService siteProductDetailStructureService;
    private final SiteProductEditStructureService siteProductEditStructureService;

    @GetMapping("/")
    public ResponseEntity<ApiResponseDTO<Object>> searchSiteProduct(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String productCode,
            @RequestParam(required = false) UUID productTypeId,
            @RequestParam(required = false) int size,
            @RequestParam(required = false) int offset) {

        SiteProductSearchInput input = new SiteProductSearchInput();
        input.setProductName(productName);
        input.setProductCode(productCode);
        input.setProductTypeId(productTypeId);
        input.setSize(size);
        input.setOffset(offset);

        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, siteProductSearchService.searchSiteProduct(input));
    }
    @GetMapping("/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> getSiteProductDetail(@PathVariable UUID siteProductId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, siteProductDetailService.getSiteProductDetail(siteProductId));
    }
    @PostMapping("/")
    public ResponseEntity<ApiResponseDTO<Object>> createSiteProduct(@RequestBody SiteProductCreateInput request,
                                                                    @RequestHeader("login-id") String loginId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.CREATED, siteProductCreateService.createSiteProduct(request, loginId));
    }
    @PutMapping("/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> editSiteProduct(@RequestBody SiteProductEditInput request,
                                                                  @RequestHeader("login-id") String loginId,
                                                                  @PathVariable UUID siteProductId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.ACCEPTED, siteProductEditService.editSiteProduct(request, loginId, siteProductId));
    }
    @DeleteMapping("/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> deleteSiteProduct(@RequestHeader("login-id") String loginId,
                                                                  @PathVariable UUID siteProductId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.ACCEPTED, siteProductDeleteService.deleteSiteProduct(siteProductId,loginId));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponseDTO<Object>> getSiteProductList() {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, siteProductListService.getSiteProductList());
    }
    @GetMapping("/list/{productTypeId}")
    public ResponseEntity<ApiResponseDTO<Object>> getSiteProductListByProductType(@PathVariable UUID productTypeId) {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, siteProductListService.getSiteProductListByProductType(productTypeId));
    }
    @GetMapping("/structure/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> getSiteProductStructureDetail(@PathVariable UUID siteProductId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, siteProductDetailStructureService.getSiteProductStructureDetail(siteProductId));
    }
    @GetMapping("/structure/edit/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> getEditDetail(@PathVariable UUID siteProductId) {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, siteProductDetailStructureService.getEditDetail(siteProductId));
    }
    @PutMapping("/structure/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> editSiteProductStructure(@RequestBody SiteProductEditStructureInput request,
                                                                           @RequestHeader("login-id") String loginId,
                                                                           @PathVariable UUID siteProductId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.ACCEPTED, siteProductEditStructureService.editSiteProductStructure(request, loginId, siteProductId));
    }
}
