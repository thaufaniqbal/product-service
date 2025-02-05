package com.iconnect.product.controller.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.ApiResponseDTO;
import com.iconnect.product.dto.customer.create.CustomerCreateInput;
import com.iconnect.product.dto.integration.IntCompanyCustomerProductMapping;
import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchInput;
import com.iconnect.product.dto.product.type.ProductTypeCreateInput;
import com.iconnect.product.dto.product.type.ProductTypeSearchInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/integration/v1")
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
public class IntegrationController {
    //customer
    @PostMapping ("/customer")
    public ResponseEntity<ApiResponseDTO<Object>> createCustomer(@RequestHeader("user-id") UUID userId,
                                                                 @RequestBody CustomerCreateInput input) throws JsonProcessingException {
        return null;
    }
    @PostMapping ("/customer/product-mapping")
    public ResponseEntity<ApiResponseDTO<Object>> companyCustomerProductMapping(@RequestHeader("user-id") UUID userId,
                                                                                @RequestBody IntCompanyCustomerProductMapping input) throws JsonProcessingException {
        return null;
    }
    @GetMapping ("/customer/get-credential/{customerId}")
    public ResponseEntity<ApiResponseDTO<Object>> getCredential(@RequestHeader("user-id") String userId,
                                                                @PathVariable UUID customerId) throws JsonProcessingException {
        return null;
    }

    // site product
    @PostMapping("/site-product/")
    public ResponseEntity<ApiResponseDTO<Object>> createSiteProduct(@RequestBody SiteProductCreateInput input,
                                                                    @RequestHeader("login-id") String loginId,
                                                                    @RequestHeader("user-id") UUID userId) throws JsonProcessingException {
        return null;
    }
    @GetMapping("/site-product/list")
    public ResponseEntity<ApiResponseDTO<Object>> getSiteProductList(@RequestHeader("user-id") UUID userId) {

        return null;
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

        return null;
    }
    //product type
    @PostMapping("/product-type")
    public ResponseEntity<ApiResponseDTO<Object>> createProductType(@RequestBody ProductTypeCreateInput input,
                                                                    @RequestHeader("login-id") String loginId,
                                                                    @RequestHeader("user-id") UUID userId) throws JsonProcessingException {
        return null;
    }
    @GetMapping("/product-type/list")
    public ResponseEntity<ApiResponseDTO<Object>> getAllProductCode(@RequestHeader("user-id") UUID userId) {
        return null;
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

        return null;
    }

    @GetMapping("/product-type/list")
    public ResponseEntity<ApiResponseDTO<Object>> getCard(@RequestHeader("user-id") UUID userId) {
        return null;
    }
}
