package com.iconnect.product.controller.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.ApiResponseDTO;
import com.iconnect.product.dto.transaction.CustomerTransactionDataInput;
import com.iconnect.product.service.transaction.customer.CustomerTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer/v1/transaction")
public class CustomerTransactionController {
    private final CustomerTransactionService customerTransactionService;

    @GetMapping("/list/{userId}")
    public ResponseEntity<ApiResponseDTO<Object>> getProductList(@PathVariable UUID userId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, customerTransactionService.getProductList(userId));
    }
    @GetMapping("/get-data/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> getData(@PathVariable UUID siteProductId,
                                                          @RequestHeader("user-id") UUID userId) throws IOException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, customerTransactionService.getData(userId, siteProductId));
    }
    @PostMapping("/save/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> saveData(@PathVariable UUID siteProductId,
                                                           @RequestHeader("user-id") UUID userId,
                                                           @RequestBody String input) throws JsonProcessingException {
        CustomerTransactionDataInput input1 = new CustomerTransactionDataInput();
        input1.setData(input);
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, customerTransactionService.saveData(userId, siteProductId, input1));
    }

}
