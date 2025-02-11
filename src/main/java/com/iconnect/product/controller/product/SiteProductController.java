package com.iconnect.product.controller.product;

import com.iconnect.product.dto.ApiResponseDTO;
import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.dto.product.site.product.SiteProductEditInput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchInput;
import com.iconnect.product.dto.product.site.structure.edit.SiteProductEditStructure;
import com.iconnect.product.dto.product.site.template.SiteProductTemplateInput;
import com.iconnect.product.service.product.product.site.product.create.SiteProductCreateService;
import com.iconnect.product.service.product.product.site.product.delete.SiteProductDeleteService;
import com.iconnect.product.service.product.product.site.product.detail.SiteProductDetailService;
import com.iconnect.product.service.product.product.site.product.edit.SiteProductEditService;
import com.iconnect.product.service.product.product.site.product.list.SiteProductListService;
import com.iconnect.product.service.product.product.site.product.search.SiteProductSearchService;
import com.iconnect.product.service.product.product.site.structure.detail.SiteProductDetailStructureService;
import com.iconnect.product.service.product.product.site.structure.edit.SiteProductEditStructureService;
import com.iconnect.product.service.product.product.site.template.create.SiteProductTemplateCreateService;
import com.iconnect.product.service.product.product.site.template.detail.SiteProductTemplateDetailService;
import com.iconnect.product.service.product.product.site.template.list.SiteProductTemplateListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/site-product")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class SiteProductController {
    private final SiteProductSearchService siteProductSearchService;
    private final SiteProductDetailService siteProductDetailService;
    private final SiteProductCreateService siteProductCreateService;
    private final SiteProductEditService siteProductEditService;
    private final SiteProductDeleteService siteProductDeleteService;
    private final SiteProductListService siteProductListService;
    private final SiteProductDetailStructureService siteProductDetailStructureService;
    private final SiteProductEditStructureService siteProductEditStructureService;
    private final SiteProductTemplateCreateService templateCreateService;
    private final SiteProductTemplateDetailService templateDetailService;
    private final SiteProductTemplateListService templateListService;

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
    public ResponseEntity<ApiResponseDTO<Object>> editSiteProductStructure(@RequestBody SiteProductEditStructure request,
                                                                           @RequestHeader("login-id") String loginId,
                                                                           @PathVariable UUID siteProductId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.ACCEPTED, siteProductEditStructureService.editSiteProductStructure(request, loginId, siteProductId));
    }
    @GetMapping("/template/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> getProductTemplate(@PathVariable UUID siteProductId) {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, templateDetailService.getProductTemplate(siteProductId));
    }
    @GetMapping("/template/not-in")
    public ResponseEntity<ApiResponseDTO<Object>> getTemplateNotInList(@PathVariable UUID siteProductId) {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, templateListService.getTemplateNotInList(siteProductId));
    }
    @PostMapping("/template/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> createSiteProduct(@RequestBody SiteProductTemplateInput request, @PathVariable UUID siteProductId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.CREATED, templateCreateService.createProductTemplate(request, siteProductId));
    }
}
