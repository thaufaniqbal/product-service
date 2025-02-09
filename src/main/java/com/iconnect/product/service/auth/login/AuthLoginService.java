package com.iconnect.product.service.auth.login;

import com.iconnect.product.dto.auth.login.AuthLoginInput;

public interface AuthLoginService {
    boolean login (AuthLoginInput input);
}
