package com.iconnect.product.controller.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.ApiResponseDTO;
import com.iconnect.product.dto.transaction.DeviceTransactionInputOutput;
import com.iconnect.product.service.transaction.device.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/device/v1/transaction")
public class DeviceTransactionController {
    private final DeviceService deviceService;

    @GetMapping("/get-data/{siteProductId}")
    public ResponseEntity<ApiResponseDTO<Object>> getData(@PathVariable UUID siteProductId,
                                                          @RequestHeader("user-id") UUID userId) throws IOException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.OK, deviceService.getData(userId, siteProductId));
    }

    @PostMapping("/save-data")
    public ResponseEntity<ApiResponseDTO<Object>> saveData(@RequestBody DeviceTransactionInputOutput input,
                                                           @RequestHeader("user-id") UUID userId) throws JsonProcessingException {
        return ApiResponseDTO.toResponseEntity(HttpStatus.CREATED, deviceService.saveData(userId, input));
    }
}