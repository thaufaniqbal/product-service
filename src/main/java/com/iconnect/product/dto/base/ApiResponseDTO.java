package com.iconnect.product.dto.base;

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
public class ApiResponseDTO<T> {

    private HttpStatus status;
    private T result;
    private String message;

    public ApiResponseDTO(HttpStatus status, T result) {
        this.status = status;
        this.result = result;
        this.message = "";
    }
    public ResponseEntity<ApiResponseDTO<T>> toResponseEntity() {
        return ResponseEntity.status(this.status).body(this);
    }
    public static <T> ResponseEntity<ApiResponseDTO<T>> toResponseEntity(HttpStatus status, T result) {
        return new ApiResponseDTO<>(status, result).toResponseEntity();
    }
}
