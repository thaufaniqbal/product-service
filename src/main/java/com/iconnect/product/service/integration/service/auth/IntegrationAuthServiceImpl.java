package com.iconnect.product.service.integration.service.auth;

import com.iconnect.product.dto.auth.login.AuthLoginInput;
import com.iconnect.product.dto.integration.IntAuthLoginInput;
import com.iconnect.product.dto.integration.IntAuthLoginOutput;
import com.iconnect.product.entity.auth.EntityCredential;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.company.Company;
import com.iconnect.product.entity.integration.CompanyCustomer;
import com.iconnect.product.repository.auth.EntityCredentialRepository;
import com.iconnect.product.repository.auth.EntityUserCompanyRepository;
import com.iconnect.product.repository.company.CompanyRepository;
import com.iconnect.product.repository.integration.CompanyCustomerRepository;
import com.iconnect.product.service.auth.login.AuthLoginService;
import com.iconnect.product.service.integration.validator.IntegrationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class IntegrationAuthServiceImpl implements IntegrationAuthService {
    private final EntityCredentialRepository credentialRepository;
    private final EntityUserCompanyRepository entityUserCompanyRepository;
    private final CompanyRepository companyRepository;
    private final CompanyCustomerRepository companyCustomerRepository;

    private final AuthLoginService authLoginService;
    private final IntegrationUtil integrationUtil;

    @Override
    public Object login(IntAuthLoginInput input) {
        IntAuthLoginOutput result = new IntAuthLoginOutput();
        AuthLoginInput authLogin = new AuthLoginInput();
        authLogin.setUsername(input.getUsername());
        authLogin.setPassword(input.getPassword());
        if (authLoginService.login(authLogin)){
            EntityCredential entityCredential = credentialRepository.findByUserNameIgnoreCase(authLogin.getUsername());
            EntityUserCompany entityUserCompany = integrationUtil.getOrCheckCompanyUser(entityCredential.getUserId());
            Company company = companyRepository.findById(entityUserCompany.getCompanyId()).orElse(null);
            if (Objects.nonNull(company)){
                result.setLoginId(entityCredential.getUserName());
                result.setUserId(entityCredential.getUserId());
                result.setCompanyLabel(company.getCompanyName());
                result.setRole("super-user");
            }
        }

        return result;
    }

    @Override
    public Object loginCustomer(IntAuthLoginInput input) {
        IntAuthLoginOutput result = new IntAuthLoginOutput();
        AuthLoginInput authLogin = new AuthLoginInput();
        authLogin.setUsername(input.getUsername());
        authLogin.setPassword(input.getPassword());
        if (authLoginService.login(authLogin)){
            EntityCredential entityCredential = credentialRepository.findByUserNameIgnoreCase(authLogin.getUsername());
            CompanyCustomer entityUserCompany = integrationUtil.getOrCheckCompanyCustomer(entityCredential.getUserId());
            Company company = companyRepository.findById(entityUserCompany.getCompanyId()).orElse(null);
            if (Objects.nonNull(company)){
                result.setLoginId(entityCredential.getUserName());
                result.setUserId(entityCredential.getUserId());
                result.setCompanyLabel(company.getCompanyName());
                result.setRole("user");
            }
        }

        return result;
    }
}
