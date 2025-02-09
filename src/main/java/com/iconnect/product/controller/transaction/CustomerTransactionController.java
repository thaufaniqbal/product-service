package com.iconnect.product.controller.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.ApiResponseDTO;
import com.iconnect.product.dto.transaction.CustomerTransactionDataInput;
import com.iconnect.product.service.transaction.CustomerTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer/v1/transaction")
@CrossOrigin(methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
public class CustomerTransactionController {
    private final CustomerTransactionService customerTransactionService;

    @PostMapping("/list/{companyId}")
    public ResponseEntity<ApiResponseDTO<Object>> getProductList(@PathVariable UUID companyId,
                                                                 @RequestBody CustomerTransactionDataInput input) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, customerTransactionService.getProductList(companyId, input));
    }
    @PostMapping("/{companyId}")
    public ResponseEntity<ApiResponseDTO<Object>> getData(@PathVariable UUID companyId,
                                                                @RequestBody CustomerTransactionDataInput input) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, customerTransactionService.getData(companyId, input));
    }
    @PostMapping("/save")
    public ResponseEntity<ApiResponseDTO<Object>> saveData(@RequestBody CustomerTransactionDataInput input) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, customerTransactionService.saveData(input));
    }

}
