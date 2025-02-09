package com.iconnect.product.service.integration.auth;

import com.iconnect.product.dto.integration.IntAuthLoginInput;

public interface IntegrationAuthService {
    Object login (IntAuthLoginInput input);
}
