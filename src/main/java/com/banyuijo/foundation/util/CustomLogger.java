package com.banyuijo.foundation.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomLogger {
    private final ObjectMapper objectMapper;
    public void setLogObject(Object object) throws JsonProcessingException {
        log.info(objectMapper.writeValueAsString(object));
    }
}
