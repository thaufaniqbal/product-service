package com.banyuijo.foundation.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiResponseDto <T> {

    private HttpStatus status;
    private T result;
    private String error;

    public ApiResponseDto(HttpStatus status, T result) {
        this.status = status;
        this.result = result;
    }
    public ResponseEntity<ApiResponseDto<T>> toResponseEntity() {
        return ResponseEntity.status(this.status).body(this);
    }
    public static <T> ResponseEntity<ApiResponseDto<T>> toResponseEntity(HttpStatus status, T result) {
        return new ApiResponseDto<>(status, result).toResponseEntity();
    }
}
