package com.iconnect.product.service.integration.company.customer;

import com.iconnect.product.dto.auth.register.AuthRegisterInput;
import com.iconnect.product.dto.customer.create.CustomerCreateInput;
import com.iconnect.product.dto.integration.*;
import com.iconnect.product.entity.auth.EntityCredential;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.customer.Customer;
import com.iconnect.product.entity.integration.CompanyCustomer;
import com.iconnect.product.entity.integration.CustomerSiteProduct;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.auth.EntityCredentialRepository;
import com.iconnect.product.repository.auth.EntityUserCompanyRepository;
import com.iconnect.product.repository.customer.CustomerRepository;
import com.iconnect.product.repository.integration.CompanyCustomerRepository;
import com.iconnect.product.repository.integration.CustomerSiteProductRepository;
import com.iconnect.product.service.auth.register.AuthRegisterService;
import com.iconnect.product.service.customer.create.CustomerCreateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CompanyCustomerServiceImpl implements CompanyCustomerService {
    private final CompanyCustomerRepository companyCustomerRepository;
    private final EntityUserCompanyRepository entityUserCompanyRepository;
    private final EntityCredentialRepository entityCredentialRepository;
    private final CustomerRepository customerRepository;
    private final CustomerSiteProductRepository customerSiteProductRepository;
    private final CustomerCreateService customerCreateService;
    private final AuthRegisterService authRegisterService;
    @Override
    @Transactional
    public Object createCustomerByCompany(UUID userId, IntCompanyCustomerInput input) {
        EntityUserCompany entityUserCompany = getEntityUser(userId);

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
        EntityUserCompany entityUserCompany = getEntityUser(userId);
        List<CompanyCustomer> companyCustomers =  companyCustomerRepository.findAllByCompanyId(entityUserCompany.getCompanyId());
        List<UUID> customerUUIDS =  companyCustomers.stream()
                .map(CompanyCustomer::getCustomerId).collect(toList());
        List<Customer> customers = customerRepository.findAllById(customerUUIDS);

        List<IntCompanyCustomerSearchOutput> results = new ArrayList<>();
        for (CompanyCustomer companyCustomer : companyCustomers){
            Customer customer = customers.stream()
                    .filter(customerFilter -> customerFilter.getCustomerId().equals(companyCustomer.getCustomerId()))
                    .findFirst().orElse(null);
            if (Objects.nonNull(customer)){
                IntCompanyCustomerSearchOutput result = new IntCompanyCustomerSearchOutput();
                result.setCustomerId(customer.getCustomerId());
                result.setCustomerName(customer.getCustomerName());
                result.setStatus(BooleanStatus.YES.getCode());
                results.add(result);
            }
        }
        return results;
    }

    @Override
    public IntCompanyCustomerCredentialOutput getCustomerCredential(UUID userId, UUID customerId) {
        IntCompanyCustomerCredentialOutput result = new IntCompanyCustomerCredentialOutput();
        EntityUserCompany entityUserCompany = getEntityUser(userId);
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
        List<IntCompanyCustomerProductMappingOutput> results = new ArrayList<>();
        EntityUserCompany entityUserCompany = getEntityUser(userId);
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

    private EntityUserCompany getEntityUser (UUID userId) {
        EntityUserCompany result = entityUserCompanyRepository.findById(userId).orElse(null);
        if (Objects.isNull(result)){
            throw new HttpStatusException(HttpStatusCode.AUTH_DATA_NOT_FOUND);
        }
        return result;
    }
}
