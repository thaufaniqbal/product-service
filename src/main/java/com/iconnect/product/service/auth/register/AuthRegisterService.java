package com.iconnect.product.service.auth.register;

import com.iconnect.product.dto.auth.register.AuthRegisterInput;

import java.util.UUID;

public interface AuthRegisterService {
    Object register (AuthRegisterInput input, UUID userId);
}
