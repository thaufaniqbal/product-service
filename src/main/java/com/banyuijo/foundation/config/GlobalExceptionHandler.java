package com.banyuijo.foundation.config;

import com.banyuijo.foundation.dto.base.ApiResponseDto;
import com.banyuijo.foundation.exception.HttpStatusException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @ExceptionHandler(HttpStatusException.class)
    public final ResponseEntity<ApiResponseDto> error(HttpStatusException ex) throws JsonProcessingException {
        ApiResponseDto response = new ApiResponseDto(ex.getError().getStatus(), new HashMap<>(), ex.getError().getFormat());
        log.error(objectMapper.writeValueAsString(response));
        return new ResponseEntity<>(response, ex.getError().getStatus());
    }

}
