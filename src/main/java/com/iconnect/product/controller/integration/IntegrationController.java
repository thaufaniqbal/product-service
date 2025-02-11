package com.iconnect.product.controller.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.ApiResponseDTO;
import com.iconnect.product.dto.integration.IntAuthLoginInput;
import com.iconnect.product.dto.integration.IntCompanyCustomerInput;
import com.iconnect.product.dto.integration.IntCompanyCustomerProductMappingInput;
import com.iconnect.product.dto.integration.IntCompanyCustomerSearchInput;
import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchInput;
import com.iconnect.product.dto.product.type.ProductTypeCreateInput;
import com.iconnect.product.dto.product.type.ProductTypeSearchInput;
import com.iconnect.product.service.integration.auth.IntegrationAuthService;
import com.iconnect.product.service.integration.company.customer.CompanyCustomerService;
import com.iconnect.product.service.integration.company.product.site.CompanySiteProductService;
import com.iconnect.product.service.integration.company.product.type.CompanyProductTypeService;
import com.iconnect.product.service.integration.customer.CustomerSiteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/integration/v1")
//@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
public class IntegrationController {
    private final CompanyCustomerService companyCustomerService;
    private final CompanySiteProductService companySiteProductService;
    private final CompanyProductTypeService companyProductTypeService;
    private final CustomerSiteProductService customerSiteProductService;
    private final IntegrationAuthService integrationAuthService;

    //company customer
    @PostMapping ("/customer/")
    public ResponseEntity<ApiResponseDTO<Object>> createCustomer(@RequestHeader("user-id") UUID userId,
                                                                 @RequestBody IntCompanyCustomerInput input) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.CREATED, companyCustomerService.createCustomerByCompany(userId, input));
    }
    @PostMapping ("/customer/search")
    public ResponseEntity<ApiResponseDTO<Object>> searchCustomer(@RequestHeader("user-id") UUID userId,
                                                                 @RequestBody IntCompanyCustomerSearchInput input) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, companyCustomerService.searchCustomer(userId, input));
    }
    @GetMapping ("/customer/get-credential/{customerId}")
    public ResponseEntity<ApiResponseDTO<Object>> getCredential(@RequestHeader("user-id") UUID userId,
                                                                @PathVariable UUID customerId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, companyCustomerService.getCustomerCredential(userId, customerId));
    }
    @PostMapping ("/customer/product-mapping/")
    public ResponseEntity<ApiResponseDTO<Object>> companyCustomerProductMapping(@RequestHeader("user-id") UUID userId,
                                                                                @RequestBody IntCompanyCustomerProductMappingInput input) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.CREATED, customerSiteProductService.customerSiteProductMapping(userId, input));
    }
    @GetMapping ("/customer/product-mapping/{customerId}")
    public ResponseEntity<ApiResponseDTO<Object>> getCustomerProductMapping(@RequestHeader("user-id") UUID userId,
                                                                @PathVariable UUID customerId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, companyCustomerService.getCustomerProductMapping(userId, customerId));
    }
    // company site product
    @PostMapping("/site-product/")
    public ResponseEntity<ApiResponseDTO<Object>> createSiteProduct(@RequestBody SiteProductCreateInput input,
                                                                    @RequestHeader("login-id") String loginId,
                                                                    @RequestHeader("user-id") UUID userId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, companySiteProductService.createProductByCompany(userId, loginId, input));
    }
    @GetMapping("/site-product/list")
    public ResponseEntity<ApiResponseDTO<Object>> getSiteProductList(@RequestHeader("user-id") UUID userId) {

        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, companySiteProductService.getSiteProductList(userId));
    }
    @GetMapping("/site-product/")
    public ResponseEntity<ApiResponseDTO<Object>> searchSiteProduct(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String productCode,
            @RequestParam(required = false) UUID productTypeId,
            @RequestParam(required = false) int size,
            @RequestParam(required = false) int offset,
            @RequestHeader("user-id") UUID userId) {

        SiteProductSearchInput input = new SiteProductSearchInput();
        input.setProductName(productName);
        input.setProductCode(productCode);
        input.setProductTypeId(productTypeId);
        input.setSize(size);
        input.setOffset(offset);

        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, companySiteProductService.searchSiteProduct(userId, input));
    }
    //company product type
    @PostMapping("/product-type/")
    public ResponseEntity<ApiResponseDTO<Object>> createProductType(@RequestBody ProductTypeCreateInput input,
                                                                    @RequestHeader("login-id") String loginId,
                                                                    @RequestHeader("user-id") UUID userId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, companyProductTypeService.createProductTypeByCompany(userId, loginId, input));
    }
    @GetMapping("/product-type/list")
    public ResponseEntity<ApiResponseDTO<Object>> getAllProductCode(@RequestHeader("user-id") UUID userId) {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, companyProductTypeService.getProductTypeList(userId));
    }

    @GetMapping("/product-type/")
    public ResponseEntity<ApiResponseDTO<Object>> searchProductType(
            @RequestParam(required = false) String productTypeCode,
            @RequestParam(required = false) String productTypeName,
            @RequestParam(required = false) int size,
            @RequestParam(required = false) int offset,
            @RequestHeader("user-id") UUID userId) {

        ProductTypeSearchInput input = new ProductTypeSearchInput();
        input.setProductTypeCode(productTypeCode);
        input.setProductTypeName(productTypeName);
        input.setSize(size);
        input.setOffset(offset);

        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, companyProductTypeService.searchProductType(userId, input));
    }

    //company login
    @PostMapping ("/login")
    public ResponseEntity<ApiResponseDTO<Object>> login(@RequestBody IntAuthLoginInput input) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.ACCEPTED, integrationAuthService.login(input));
    }

    //customer login
    @PostMapping ("/login-customer")
    public ResponseEntity<ApiResponseDTO<Object>> loginCustomer(@RequestBody IntAuthLoginInput input) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.ACCEPTED, integrationAuthService.loginCustomer(input));
    }
}
