package com.iconnect.product.service.integration.auth;

import com.iconnect.product.dto.auth.login.AuthLoginInput;
import com.iconnect.product.dto.integration.IntAuthLoginInput;
import com.iconnect.product.dto.integration.IntAuthLoginOutput;
import com.iconnect.product.entity.auth.EntityCredential;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.company.Company;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.auth.EntityCredentialRepository;
import com.iconnect.product.repository.auth.EntityUserCompanyRepository;
import com.iconnect.product.repository.company.CompanyRepository;
import com.iconnect.product.service.auth.login.AuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IntegrationAuthServiceImpl implements IntegrationAuthService {
    private final EntityCredentialRepository credentialRepository;
    private final EntityUserCompanyRepository entityUserCompanyRepository;
    private final CompanyRepository companyRepository;

    private final AuthLoginService authLoginService;

    @Override
    public Object login(IntAuthLoginInput input) {
        IntAuthLoginOutput result = new IntAuthLoginOutput();
        AuthLoginInput authLogin = new AuthLoginInput();
        authLogin.setUserName(input.getUserName());
        authLogin.setPassword(input.getPassword());
        if (authLoginService.login(authLogin)){
            EntityCredential entityCredential = credentialRepository.findByUserNameIgnoreCase(authLogin.getUserName());
            EntityUserCompany entityUserCompany = getEntityUser(entityCredential.getUserId());
            Company company = companyRepository.findById(entityUserCompany.getCompanyId()).orElse(null);
            if (Objects.nonNull(company)){
                result.setLoginId(entityCredential.getUserName());
                result.setUserId(entityCredential.getUserId());
                result.setCompanyLabel(company.getCompanyName());
            }
        }

        return result;
    }
    private EntityUserCompany getEntityUser (UUID userId) {
        EntityUserCompany result = entityUserCompanyRepository.findById(userId).orElse(null);
        if (Objects.isNull(result)){
            throw new HttpStatusException(HttpStatusCode.AUTH_DATA_NOT_FOUND);
        }
        return result;
    }
}
