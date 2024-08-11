package com.banyuijo.foundation.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

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

}
