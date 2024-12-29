package com.iconnect.product.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomLogger {
    private final ObjectMapper objectMapper;
    public void setLogObject(Object object) throws JsonProcessingException {
        log.info(objectMapper.writeValueAsString(object));
    }
    public void setLogObject(Object object, String service, String loginId) throws JsonProcessingException {
        HashMap<String, String> data = new HashMap<>();
        data.put("loginId: ", loginId);
        data.put("Service: ", service);
        data.put("Object : ", objectMapper.writeValueAsString(object));
        log.info(objectMapper.writeValueAsString(data));
    }
}
