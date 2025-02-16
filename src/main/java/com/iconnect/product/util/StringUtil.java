package com.iconnect.product.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StringUtil {
    public static byte[] byteConvert(Object var) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String savedData = mapper.writeValueAsString(var);
        String cleanedData = savedData.replace("\\r\\n", "").replace("\\\"", "\"");
        log.info(cleanedData);
        return cleanedData.substring(1, cleanedData.length() - 1).getBytes();
    }
}
