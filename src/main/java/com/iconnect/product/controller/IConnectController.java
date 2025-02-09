package com.iconnect.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.ApiResponseDTO;
import com.iconnect.product.dto.auth.register.AuthCompanyUserRegisterInput;
import com.iconnect.product.dto.company.create.CompanyCreateInput;
import com.iconnect.product.service.auth.register.AuthRegisterService;
import com.iconnect.product.service.company.create.CompanyCreateService;
import com.iconnect.product.service.company.list.CompanyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/iconnect")
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
public class IConnectController {
    private final AuthRegisterService authRegisterService;
    private final CompanyCreateService companyCreateService;
    private final CompanyListService companyListService;

    @PostMapping("/register/user-company")
    public ResponseEntity<ApiResponseDTO<Object>> searchCustomer(@RequestHeader("user-id") UUID userId,
                                                                 @RequestBody AuthCompanyUserRegisterInput input) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.CREATED, authRegisterService.registerUserCompany(input, null));
    }

    @PostMapping("/company/")
    public ResponseEntity<ApiResponseDTO<Object>> createCompany(@RequestHeader("user-id") UUID userId,
                                                                 @RequestBody CompanyCreateInput input) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.CREATED, companyCreateService.create(input, null));
    }
    @GetMapping("/company/list")
    public ResponseEntity<ApiResponseDTO<Object>> getCompanyList() throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.CREATED, companyListService.getCompanyList());
    }
}
