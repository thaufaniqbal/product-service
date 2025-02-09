package com.iconnect.product.service.transaction;

import com.iconnect.product.dto.integration.IntCompanyCustomerProductMappingOutput;
import com.iconnect.product.dto.transaction.CustomerTransactionDataInput;
import com.iconnect.product.dto.transaction.CustomerTransactionDataOutput;
import com.iconnect.product.entity.integration.CompanyCustomer;
import com.iconnect.product.entity.integration.CustomerSiteProduct;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.integration.CompanyCustomerRepository;
import com.iconnect.product.repository.integration.CustomerSiteProductRepository;
import com.iconnect.product.service.integration.company.customer.CompanyCustomerService;
import com.iconnect.product.service.product.product.site.template.detail.SiteProductTemplateDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerTransactionServiceImpl implements CustomerTransactionService {
    private final CompanyCustomerRepository companyCustomerRepository;
    private final CustomerSiteProductRepository customerSiteProductRepository;

    private final CompanyCustomerService companyCustomerService;
    private final SiteProductTemplateDetailService templateDetailService;

    @Override
    public Object getData(UUID userId, UUID siteProductId) {
        CustomerTransactionDataOutput result = new CustomerTransactionDataOutput();
        CompanyCustomer entityUserCompany = getCompanyCustomer(userId);
        //logic
        return templateDetailService.getProductTemplate(siteProductId);
    }

    @Override
    public Object getProductList(UUID userId) {
        CompanyCustomer entityUserCompany = getCompanyCustomer(userId);
        List<IntCompanyCustomerProductMappingOutput> results = new ArrayList<>();
        List<CustomerSiteProduct> customerSiteProducts = customerSiteProductRepository.findAllByCompanyId(entityUserCompany.getCompanyId());
        if (!customerSiteProducts.isEmpty()){
            for (var customerSiteProduct : customerSiteProducts){
                if (customerSiteProduct.getCustomerId().equals(userId)){
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

    @Override
    public Object saveData(UUID userId, UUID siteProductId, CustomerTransactionDataInput input) {
        CompanyCustomer entityUserCompany = getCompanyCustomer(userId);

        return null;
    }
    private CompanyCustomer getCompanyCustomer (UUID userId) {
        CompanyCustomer result = companyCustomerRepository.findByCustomerId(userId).orElse(null);
        if (Objects.isNull(result)){
            throw new HttpStatusException(HttpStatusCode.AUTH_DATA_NOT_FOUND);
        }
        return result;
    }
}

