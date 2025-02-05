package com.iconnect.product.service.auth.login;

import com.iconnect.product.dto.auth.login.AuthLoginInput;

public interface AuthLoginService {
    Boolean login (AuthLoginInput input);
}
