package com.iconnect.product.service.transaction.customer;

import com.iconnect.product.dto.integration.IntCompanyCustomerProductMappingOutput;
import com.iconnect.product.dto.transaction.CustomerTransactionDataInput;
import com.iconnect.product.dto.transaction.CustomerTransactionDataOutput;
import com.iconnect.product.entity.integration.CompanyCustomer;
import com.iconnect.product.entity.integration.CustomerSiteProduct;
import com.iconnect.product.repository.integration.CompanyCustomerRepository;
import com.iconnect.product.repository.integration.CustomerSiteProductRepository;
import com.iconnect.product.service.integration.service.company.customer.CompanyCustomerService;
import com.iconnect.product.service.integration.validator.IntegrationUtil;
import com.iconnect.product.service.product.product.site.template.detail.SiteProductTemplateDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerTransactionServiceImpl implements CustomerTransactionService {
    private final CompanyCustomerRepository companyCustomerRepository;
    private final CustomerSiteProductRepository customerSiteProductRepository;

    private final CompanyCustomerService companyCustomerService;
    private final SiteProductTemplateDetailService templateDetailService;
    private final IntegrationUtil integrationUtil;

    @Override
    public Object getData(UUID userId, UUID siteProductId) {
        CustomerTransactionDataOutput result = new CustomerTransactionDataOutput();
        CompanyCustomer entityUserCompany = integrationUtil.getOrCheckCompanyCustomer(userId);
        //logic
        return templateDetailService.getProductTemplate(siteProductId);
    }

    @Override
    public Object getProductList(UUID userId) {
        CompanyCustomer entityUserCompany = integrationUtil.getOrCheckCompanyCustomer(userId);
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
        CompanyCustomer entityUserCompany = integrationUtil.getOrCheckCompanyCustomer(userId);

        return null;
    }
}

