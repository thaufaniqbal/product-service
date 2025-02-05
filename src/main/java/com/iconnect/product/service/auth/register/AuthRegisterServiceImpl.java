package com.iconnect.product.service.auth.register;

import com.iconnect.product.dto.auth.register.AuthRegisterInput;
import com.iconnect.product.entity.auth.EntityCredential;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.auth.EntityCredentialRepository;
import com.iconnect.product.service.auth.validator.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthRegisterServiceImpl implements AuthRegisterService {
    private final EntityCredentialRepository credentialRepository;
    private final AuthValidator validator ;

    @Override
    public Object register(AuthRegisterInput input, UUID userId) {
        validator.isExistUsername(input.getUsername());
        if (!input.getFirstPassword().equalsIgnoreCase(input.getSecondPassword())){
            throw new HttpStatusException(HttpStatusCode.AUTH_FAILED_LOGIN, ", invalid password");
        }
        EntityCredential entityCredential = build(input, userId);
        credentialRepository.save(entityCredential);
        return entityCredential;
    }

    private EntityCredential build (AuthRegisterInput input, UUID userId){
        EntityCredential result = new EntityCredential();
        result.setUserId(Objects.isNull(userId) ? UUID.randomUUID() : userId);
        return build (input, result);
    }
    private EntityCredential build (AuthRegisterInput input, EntityCredential result){
        result.setUserName(input.getUsername());
        result.setPassword(input.getFirstPassword());
        result.setEntityTypeId(null);
        return result;
    }
}
