package com.iconnect.product.service.integration.service.auth;

import com.iconnect.product.dto.integration.IntAuthLoginInput;

public interface IntegrationAuthService {
    Object login (IntAuthLoginInput input);
    Object loginCustomer (IntAuthLoginInput input);
}
