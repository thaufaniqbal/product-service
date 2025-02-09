package com.iconnect.product.validator;

import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GlobalValidator {

    public void validateRequestLength(String property ,Integer minLength, Integer maxLength,
                                      HttpStatusCode minimumStatus, HttpStatusCode maximumStatus,
                                      Object... params) {
        int length = property.length();
        if (length < minLength || length > maxLength) {
            throw new HttpStatusException(
                    length < minLength ? minimumStatus : maximumStatus,
                    property,
                    length < minLength ? minLength : maxLength);
        }
    }
    public void validateRequestMandatory(Object property, HttpStatusCode httpStatusCode, Object... params){
        if (Objects.isNull(property)){
            throw new HttpStatusException(httpStatusCode, params);
        }
    }
}
