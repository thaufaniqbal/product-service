package com.iconnect.product.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum HttpStatusCode {
    DATA_NOT_FOUND (100001, HttpStatus.NO_CONTENT, false, "Data Not Found"),
    MISSING_MANDATORY_PROPERTY (100002, HttpStatus.BAD_REQUEST, true, "Missing mandatory property %s"),
    INVALID_DATA_TYPE(100003, HttpStatus.BAD_REQUEST,true,"Invalid data type for property %s"),
    MAXIMUM_LENGTH_EXCEEDED (100004, HttpStatus.BAD_REQUEST, true, "Maximum length for property %s is %s"),
    MINIMUM_LENGTH_EXCEEDED (100005, HttpStatus.BAD_REQUEST, true, "Minimum length for property %s is %s"),
    DATA_ALREADY_EXIST (100006, HttpStatus.BAD_REQUEST, true, "Data Already Exist for %s"),
    INVALID_DATA_INPUT (100007, HttpStatus.BAD_REQUEST, true, "Invalid data input for %s"),
    DATA_NOT_FOUND_FOR (100008, HttpStatus.BAD_REQUEST, true, "Data Not Found for %s"),
    FAILED_DELETE (100009, HttpStatus.NOT_ACCEPTABLE, true, "It has %d that use this %s%s");

    private final int code;
    private final HttpStatus status;
    private final boolean parameterized;
    private final String format;
}
