package com.iconnect.product.service.auth.login;

import com.iconnect.product.dto.auth.login.AuthLoginInput;
import com.iconnect.product.service.auth.validator.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthLoginServiceImpl implements AuthLoginService {
    private final AuthValidator validator ;
    @Override
    public boolean login(AuthLoginInput input) {
        validator.validateLogin(input);
        return true;
    }
}
