package com.banyuijo.product.validator;

import com.banyuijo.product.enums.HttpStatusCode;
import com.banyuijo.product.exception.HttpStatusException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GlobalValidator {

    public void validateRequestLength(String property ,Integer minLength, Integer maxLength) {
        int length = property.length();
        if (length < minLength || length > maxLength) {
            throw new HttpStatusException(
                    length < minLength ? HttpStatusCode.MINIMUM_LENGTH_EXCEEDED : HttpStatusCode.MAXIMUM_LENGTH_EXCEEDED,
                    property,
                    length < minLength ? minLength : maxLength);
        }
    }
    public void validateRequestMandatory(String property){
        if (property.isEmpty()){
            throw new HttpStatusException(HttpStatusCode.MISSING_MANDATORY_PROPERTY);
        }
    }
}
