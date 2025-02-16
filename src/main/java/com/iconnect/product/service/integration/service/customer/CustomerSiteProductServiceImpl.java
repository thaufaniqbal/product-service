package com.iconnect.product.service.integration.service.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.integration.IntCompanyCustomerProductMappingInput;
import com.iconnect.product.dto.product.site.product.SiteProductDetailOutput;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.integration.CustomerSiteProduct;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.integration.CustomerSiteProductRepository;
import com.iconnect.product.service.integration.validator.IntegrationUtil;
import com.iconnect.product.service.product.product.site.product.detail.SiteProductDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerSiteProductServiceImpl implements CustomerSiteProductService {
    private final CustomerSiteProductRepository customerSiteProductRepository;
    private final SiteProductDetailService siteProductDetailService;
    private final IntegrationUtil integrationUtil;

    @Override
    public Object customerSiteProductMapping(UUID userId, IntCompanyCustomerProductMappingInput input) throws JsonProcessingException {
        EntityUserCompany entityUserCompany = integrationUtil.getOrCheckCompanyUser(userId);
        customerSiteProductExistCheck(input.getCustomerId(), input.getSiteProductId());
        CustomerSiteProduct customerSiteProduct = new CustomerSiteProduct();
        customerSiteProduct.setCustomerSiteProductId(UUID.randomUUID());
        customerSiteProduct.setSiteProductId(input.getSiteProductId());
        customerSiteProduct.setCustomerId(input.getCustomerId());
        customerSiteProduct.setCompanyId(entityUserCompany.getCompanyId());
        customerSiteProductRepository.save(customerSiteProduct);
        return true;
    }
    private void customerSiteProductExistCheck (UUID customerId, UUID siteProductId) throws JsonProcessingException {
        CustomerSiteProduct customerSiteProduct = customerSiteProductRepository.
                findByCustomerIdAndSiteProductId(customerId, siteProductId).orElse(null);
        log.info(customerSiteProduct.getSiteProductId()+" " +customerSiteProduct.getCompanyId());
        if (Objects.nonNull(customerSiteProduct)){
            SiteProductDetailOutput siteProduct = siteProductDetailService.getSiteProductDetail(siteProductId);
            throw new HttpStatusException(HttpStatusCode.INTEGRATION_DATA_ALREADY_EXIST, siteProduct.getSiteProductName());
        }
    }
}
