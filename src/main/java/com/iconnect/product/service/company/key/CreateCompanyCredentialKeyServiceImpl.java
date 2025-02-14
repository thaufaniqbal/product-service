package com.iconnect.product.service.company.key;

import com.iconnect.product.dto.foundation.credential.key.CreateCompanyCredentialKeyInput;
import com.iconnect.product.entity.foundation.CredentialKeyCompany;
import com.iconnect.product.repository.company.CredentialKeyCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCompanyCredentialKeyServiceImpl implements CreateCompanyCredentialKeyService {
    private final CredentialKeyCompanyRepository credentialKeyCompanyRepository;
    @Override
    public Object create(CreateCompanyCredentialKeyInput input) {
        CredentialKeyCompany credentialKeyCompany = new CredentialKeyCompany();
        credentialKeyCompany.setCompanyId(input.getCompanyId());
        credentialKeyCompany.setKeyTotal(input.getKeyTotal());
        credentialKeyCompanyRepository.save(credentialKeyCompany);
        return credentialKeyCompany;
    }
}
