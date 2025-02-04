package com.iconnect.product.validator;

import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
    public void validateRequestMandatory(Object property, HttpStatusCode httpStatusCode){
        if (Objects.isNull(property)){
            throw new HttpStatusException(httpStatusCode, property.toString());
        }
    }
}
