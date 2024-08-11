package com.banyuijo.foundation.controller;

import com.banyuijo.foundation.dto.base.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class GreetingController {
    @GetMapping("/greeting")
    public ApiResponseDto greeting() {
        return new ApiResponseDto<>(HttpStatus.OK, "hi, this is my first project");
    }
}
