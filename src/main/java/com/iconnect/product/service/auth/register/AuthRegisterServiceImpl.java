package com.iconnect.product.service.auth.register;

import com.iconnect.product.dto.auth.register.AuthCompanyUserRegisterInput;
import com.iconnect.product.dto.auth.register.AuthRegisterInput;
import com.iconnect.product.entity.auth.EntityCredential;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.auth.EntityCredentialRepository;
import com.iconnect.product.repository.auth.EntityUserCompanyRepository;
import com.iconnect.product.service.auth.validator.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthRegisterServiceImpl implements AuthRegisterService {
    private final EntityCredentialRepository credentialRepository;
    private final EntityUserCompanyRepository entityUserCompanyRepository;
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

    @Override
    public Object registerUserCompany(AuthCompanyUserRegisterInput input, UUID userId) {
        validator.isExistUsername(input.getUsername());
        EntityCredential entityCredential = new EntityCredential();
        entityCredential.setUserId(UUID.randomUUID());
        entityCredential.setUserName(input.getUsername());
        entityCredential.setPassword("123");

        EntityUserCompany entityUserCompany = new EntityUserCompany();
        entityUserCompany.setUserId(entityCredential.getUserId());
        entityUserCompany.setCompanyId(input.getCompanyId());

        credentialRepository.save(entityCredential);
        entityUserCompanyRepository.save(entityUserCompany);
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
