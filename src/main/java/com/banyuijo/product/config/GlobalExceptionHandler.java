package com.banyuijo.product.config;

import com.banyuijo.product.dto.base.ApiResponseDTO;
import com.banyuijo.product.exception.HttpStatusException;
import com.banyuijo.product.util.CustomLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final ObjectMapper objectMapper;
    private final CustomLogger customLogger;
    @ExceptionHandler(HttpStatusException.class)
    public final ResponseEntity<Object>  errorData(HttpStatusException ex) throws JsonProcessingException {
        ApiResponseDTO response = new ApiResponseDTO(ex.getError().getStatus(), new HashMap<>(), ex.getMessage());
        customLogger.setLogObject(response);
        return new ResponseEntity<>(response, ex.getError().getStatus());
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponseDTO> handleException(Exception ex) throws JsonProcessingException {
        ApiResponseDTO response = new ApiResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR,
                new HashMap<>(),
                "Internal Server Error: " + ex.getMessage()
        );
        log.error("Error 500 buset, gila lu ya: \n ", objectMapper.writeValueAsString(response),"\n"+ ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
