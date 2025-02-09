package com.iconnect.product.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum HttpStatusCode {
    //product-service
    DATA_NOT_FOUND (100001, HttpStatus.NO_CONTENT, false, "Data Not Found"),
    MISSING_MANDATORY_PROPERTY (100002, HttpStatus.BAD_REQUEST, true, "Missing mandatory property %s"),
    INVALID_DATA_TYPE(100003, HttpStatus.BAD_REQUEST,true,"Invalid data type for property %s"),
    MAXIMUM_LENGTH_EXCEEDED (100004, HttpStatus.BAD_REQUEST, true, "Maximum length for property %s is %s"),
    MINIMUM_LENGTH_EXCEEDED (100005, HttpStatus.BAD_REQUEST, true, "Minimum length for property %s is %s"),
    DATA_ALREADY_EXIST (100006, HttpStatus.BAD_REQUEST, true, "Data Already Exist for %s"),
    INVALID_DATA_INPUT (100007, HttpStatus.BAD_REQUEST, true, "Invalid data input for %s"),
    DATA_NOT_FOUND_FOR (100008, HttpStatus.BAD_REQUEST, true, "Data Not Found for %s"),
    FAILED_DELETE (100009, HttpStatus.NOT_ACCEPTABLE, true, "It has %d that use this %s%s"),
    
    //company-service
    COMPANY_DATA_NOT_FOUND (200001, HttpStatus.NO_CONTENT, false, "Data Not Found"),
    COMPANY_MISSING_MANDATORY_PROPERTY (200002, HttpStatus.BAD_REQUEST, true, "Missing mandatory property %s"),
    COMPANY_INVALID_DATA_TYPE(200003, HttpStatus.BAD_REQUEST,true,"Invalid data type for property %s"),
    COMPANY_MAXIMUM_LENGTH_EXCEEDED (200004, HttpStatus.BAD_REQUEST, true, "Maximum length for property %s is %s"),
    COMPANY_MINIMUM_LENGTH_EXCEEDED (200005, HttpStatus.BAD_REQUEST, true, "Minimum length for property %s is %s"),
    COMPANY_DATA_ALREADY_EXIST (200006, HttpStatus.BAD_REQUEST, true, "Data Already Exist for %s"),
    COMPANY_INVALID_DATA_INPUT (200007, HttpStatus.BAD_REQUEST, true, "Invalid data input for %s"),
    COMPANY_DATA_NOT_FOUND_FOR (200008, HttpStatus.BAD_REQUEST, true, "Data Not Found for %s"),
    COMPANY_FAILED_DELETE (200009, HttpStatus.NOT_ACCEPTABLE, true, "It has %d that use this %s%s"),

    //customer-service
    CUSTOMER_DATA_NOT_FOUND (300001, HttpStatus.NO_CONTENT, false, "Data Not Found"),
    CUSTOMER_MISSING_MANDATORY_PROPERTY (300002, HttpStatus.BAD_REQUEST, true, "Missing mandatory property %s"),
    CUSTOMER_INVALID_DATA_TYPE(300003, HttpStatus.BAD_REQUEST,true,"Invalid data type for property %s"),
    CUSTOMER_MAXIMUM_LENGTH_EXCEEDED (300004, HttpStatus.BAD_REQUEST, true, "Maximum length for property %s is %s"),
    CUSTOMER_MINIMUM_LENGTH_EXCEEDED (300005, HttpStatus.BAD_REQUEST, true, "Minimum length for property %s is %s"),
    CUSTOMER_DATA_ALREADY_EXIST (300006, HttpStatus.BAD_REQUEST, true, "Data Already Exist for %s"),
    CUSTOMER_INVALID_DATA_INPUT (300007, HttpStatus.BAD_REQUEST, true, "Invalid data input for %s"),
    CUSTOMER_DATA_NOT_FOUND_FOR (300008, HttpStatus.BAD_REQUEST, true, "Data Not Found for %s"),
    CUSTOMER_FAILED_DELETE (300009, HttpStatus.NOT_ACCEPTABLE, true, "It has %d that use this %s%s"),

    //integration-service
    INTEGRATION_DATA_NOT_FOUND (400001, HttpStatus.NO_CONTENT, false, "Data Not Found"),
    INTEGRATION_MISSING_MANDATORY_PROPERTY (400002, HttpStatus.BAD_REQUEST, true, "Missing mandatory property %s"),
    INTEGRATION_INVALID_DATA_TYPE(400003, HttpStatus.BAD_REQUEST,true,"Invalid data type for property %s"),
    INTEGRATION_MAXIMUM_LENGTH_EXCEEDED (400004, HttpStatus.BAD_REQUEST, true, "Maximum length for property %s is %s"),
    INTEGRATION_MINIMUM_LENGTH_EXCEEDED (400005, HttpStatus.BAD_REQUEST, true, "Minimum length for property %s is %s"),
    INTEGRATION_DATA_ALREADY_EXIST (400006, HttpStatus.BAD_REQUEST, true, "Data Already Exist for %s"),
    INTEGRATION_INVALID_DATA_INPUT (400007, HttpStatus.BAD_REQUEST, true, "Invalid data input for %s"),
    INTEGRATION_DATA_NOT_FOUND_FOR (400008, HttpStatus.BAD_REQUEST, true, "Data Not Found for %s"),
    INTEGRATION_FAILED_DELETE (400009, HttpStatus.NOT_ACCEPTABLE, true, "It has %d that use this %s%s"),

    //transaction-service
    TRANSACTION_DATA_NOT_FOUND (500001, HttpStatus.NO_CONTENT, false, "Data Not Found"),
    TRANSACTION_MISSING_MANDATORY_PROPERTY (500002, HttpStatus.BAD_REQUEST, true, "Missing mandatory property %s"),
    TRANSACTION_INVALID_DATA_TYPE(500003, HttpStatus.BAD_REQUEST,true,"Invalid data type for property %s"),
    TRANSACTION_MAXIMUM_LENGTH_EXCEEDED (500004, HttpStatus.BAD_REQUEST, true, "Maximum length for property %s is %s"),
    TRANSACTION_MINIMUM_LENGTH_EXCEEDED (500005, HttpStatus.BAD_REQUEST, true, "Minimum length for property %s is %s"),
    TRANSACTION_DATA_ALREADY_EXIST (500006, HttpStatus.BAD_REQUEST, true, "Data Already Exist for %s"),
    TRANSACTION_INVALID_DATA_INPUT (500007, HttpStatus.BAD_REQUEST, true, "Invalid data input for %s"),
    TRANSACTION_DATA_NOT_FOUND_FOR (500008, HttpStatus.BAD_REQUEST, true, "Data Not Found for %s"),
    TRANSACTION_FAILED_DELETE (500009, HttpStatus.NOT_ACCEPTABLE, true, "It has %d that use this %s%s"),

    //auth-service
    AUTH_DATA_NOT_FOUND (600001, HttpStatus.NO_CONTENT, false, "Data Not Found"),
    AUTH_MISSING_MANDATORY_PROPERTY (600002, HttpStatus.BAD_REQUEST, true, "Missing mandatory property %s"),
    AUTH_INVALID_DATA_TYPE(600003, HttpStatus.BAD_REQUEST,true,"Invalid data type for property %s"),
    AUTH_MAXIMUM_LENGTH_EXCEEDED (600004, HttpStatus.BAD_REQUEST, true, "Maximum length for property %s is %s"),
    AUTH_MINIMUM_LENGTH_EXCEEDED (600005, HttpStatus.BAD_REQUEST, true, "Minimum length for property %s is %s"),
    AUTH_DATA_ALREADY_EXIST (600006, HttpStatus.BAD_REQUEST, true, "Data Already Exist for %s"),
    AUTH_INVALID_DATA_INPUT (600007, HttpStatus.BAD_REQUEST, true, "Invalid data input for %s"),
    AUTH_DATA_NOT_FOUND_FOR (600008, HttpStatus.BAD_REQUEST, true, "Data Not Found for %s"),
    AUTH_FAILED_DELETE (600009, HttpStatus.NOT_ACCEPTABLE, true, "It has %d that use this %s%s"),
    AUTH_FAILED_LOGIN (600010, HttpStatus.NOT_ACCEPTABLE, true, "Invalid Login%s");

    private final int code;
    private final HttpStatus status;
    private final boolean parameterized;
    private final String format;
}
