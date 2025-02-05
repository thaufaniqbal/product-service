package com.iconnect.product.service.auth.validator;

import com.iconnect.product.dto.auth.login.AuthLoginInput;
import com.iconnect.product.entity.auth.EntityCredential;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.auth.EntityCredentialRepository;
import com.iconnect.product.validator.GlobalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AuthValidator extends GlobalValidator {
    private final EntityCredentialRepository credentialRepository;

    public void validateLogin (AuthLoginInput input){
        validateRequestMandatory(input.getUserName(), HttpStatusCode.AUTH_MISSING_MANDATORY_PROPERTY);
        validateRequestMandatory(input.getUserName(), HttpStatusCode.AUTH_MISSING_MANDATORY_PROPERTY);
        EntityCredential credential = credentialRepository.findByUserNameIgnoreCase(input.getUserName());
        validateRequestMandatory(credential, HttpStatusCode.AUTH_FAILED_LOGIN, "");
        if (!credential.getPassword().equalsIgnoreCase(input.getPassword())){
            throw new HttpStatusException(HttpStatusCode.AUTH_FAILED_LOGIN, ", invalid password");
        }
    }

    public void isExistUsername (String userName){
        validateRequestMandatory(userName, HttpStatusCode.AUTH_MISSING_MANDATORY_PROPERTY);
        EntityCredential credential = credentialRepository.findByUserNameIgnoreCase(userName);
        if (Objects.nonNull(credential)){
            throw new HttpStatusException(HttpStatusCode.AUTH_DATA_ALREADY_EXIST,"username: " +userName);
        }
    }
}
