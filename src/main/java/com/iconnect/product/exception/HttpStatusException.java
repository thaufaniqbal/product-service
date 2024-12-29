package com.iconnect.product.exception;

import com.iconnect.product.enums.HttpStatusCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpStatusException extends RuntimeException {
    private HttpStatusCode error;
    private Object[] params;

    public HttpStatusException(HttpStatusCode error, Object... params) {
        super(error.isParameterized() ? String.format(error.getFormat(), params) : error.getFormat());
        this.error = error;
        this.params = params;
    }
}
