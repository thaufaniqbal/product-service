package com.iconnect.product.service.integration.service.company.customer;

import com.iconnect.product.dto.ResponsePageDTO;
import com.iconnect.product.dto.auth.register.AuthRegisterInput;
import com.iconnect.product.dto.customer.create.CustomerCreateInput;
import com.iconnect.product.dto.integration.*;
import com.iconnect.product.entity.auth.EntityCredential;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.integration.CompanyCustomer;
import com.iconnect.product.entity.integration.CustomerSiteProduct;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.gateway.integration.IntegrationGateway;
import com.iconnect.product.repository.auth.EntityCredentialRepository;
import com.iconnect.product.repository.integration.CompanyCustomerRepository;
import com.iconnect.product.repository.integration.CustomerSiteProductRepository;
import com.iconnect.product.service.auth.register.AuthRegisterService;
import com.iconnect.product.service.customer.create.CustomerCreateService;
import com.iconnect.product.service.integration.validator.IntegrationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyCustomerServiceImpl implements CompanyCustomerService {
    private final CompanyCustomerRepository companyCustomerRepository;
    private final EntityCredentialRepository entityCredentialRepository;
    private final CustomerSiteProductRepository customerSiteProductRepository;
    private final IntegrationGateway integrationGateway;
    private final CustomerCreateService customerCreateService;
    private final AuthRegisterService authRegisterService;
    private final IntegrationUtil integrationUtil;
    @Override
    @Transactional
    public Object createCustomerByCompany(UUID userId, IntCompanyCustomerInput input) {
        EntityUserCompany entityUserCompany = integrationUtil.getOrCheckCompanyUser(userId);

        UUID customerId = UUID.randomUUID();
        CustomerCreateInput customerCreateInput = new CustomerCreateInput();
        customerCreateInput.setCustomerName(input.getCustomerName());

        String password = generateSettingCode();
        AuthRegisterInput authRegisterInput = new AuthRegisterInput();
        authRegisterInput.setUsername(input.getCustomerName());
        authRegisterInput.setFirstPassword(password);
        authRegisterInput.setSecondPassword(password);


        CompanyCustomer companyCustomer = new CompanyCustomer();
        companyCustomer.setCompanyId(entityUserCompany.getCompanyId());
        companyCustomer.setCustomerId(customerId);

        customerCreateService.create(customerCreateInput, customerId);
        authRegisterService.register(authRegisterInput, customerId);
        companyCustomerRepository.save(companyCustomer);
        return companyCustomer;
    }

    @Override
    public Object searchCustomer(UUID userId, IntCompanyCustomerSearchInput input) {
        EntityUserCompany entityUserCompany = integrationUtil.getOrCheckCompanyUser(userId);
        Page<IntCompanyCustomerSearchOutput> resultsPage = integrationGateway.
                getSearchCustomer(entityUserCompany.getCompanyId(), input, PageRequest.of(0, 999));
        ResponsePageDTO output = new ResponsePageDTO<>();
        output.setList(resultsPage.getContent());
        output.setPage(resultsPage.getNumber());
        output.setResultPerPage(resultsPage.getSize());
        output.setTotalResult(resultsPage.getTotalElements());
        return output;
    }

    @Override
    public IntCompanyCustomerCredentialOutput getCustomerCredential(UUID userId, UUID customerId) {
        IntCompanyCustomerCredentialOutput result = new IntCompanyCustomerCredentialOutput();
        EntityUserCompany entityUserCompany = integrationUtil.getOrCheckCompanyUser(userId);
        List<CompanyCustomer> companyCustomers =  companyCustomerRepository.findAllByCompanyId(entityUserCompany.getCompanyId());
        CompanyCustomer customer = companyCustomers.stream()
                .filter(customerFilter -> customerFilter.getCustomerId().equals(customerId))
                .findFirst().orElse(null);
        if (Objects.nonNull(customer)){
            EntityCredential entityCredential = entityCredentialRepository.findById(customer.getCustomerId()).orElse(null);
            if (Objects.nonNull(entityCredential)){
                result.setUserName(entityCredential.getUserName());
                result.setPassword(entityCredential.getPassword());
                result.setIsDefault(BooleanStatus.YES.getCode());
            }
        }
        return result;
    }

    @Override
    public Object getCustomerProductMapping(UUID userId, UUID customerId) {
        EntityUserCompany entityUserCompany = integrationUtil.getOrCheckCompanyUser(userId);
        List<IntCompanyCustomerProductMappingOutput> results = new ArrayList<>();
        List<CustomerSiteProduct> customerSiteProducts = customerSiteProductRepository.findAllByCompanyId(entityUserCompany.getCompanyId());

        if (!customerSiteProducts.isEmpty()){
            for (var customerSiteProduct : customerSiteProducts){
                if (customerSiteProduct.getCustomerId().equals(customerId)){
                    IntCompanyCustomerProductMappingOutput result = new IntCompanyCustomerProductMappingOutput();
                    result.setSiteProductId(customerSiteProduct.getSiteProductId());
                    result.setCustomerId(customerSiteProduct.getCustomerId());
                    result.setProductType(null);
                    result.setProductName(null);
                    results.add(result);
                }
            }
        }
        return results;
    }
    public static String generateSettingCode() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 5);
    }
}
