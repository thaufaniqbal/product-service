package com.iconnect.product.service.integration.company.product.site;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.ResponsePageDTO;
import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.dto.product.site.product.SiteProductListOutput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchInput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchOutput;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.integration.CompanySiteProduct;
import com.iconnect.product.entity.product.SiteProduct;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.gateway.integration.IntegrationGateway;
import com.iconnect.product.repository.auth.EntityUserCompanyRepository;
import com.iconnect.product.repository.integration.CompanySiteProductRepository;
import com.iconnect.product.service.product.product.site.product.create.SiteProductCreateService;
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
public class CompanySiteProductServiceImpl implements CompanySiteProductService {
    private final EntityUserCompanyRepository entityUserCompanyRepository;
    private final CompanySiteProductRepository companySiteProductRepository;
    private final SiteProductCreateService siteProductCreateService;
    private final IntegrationGateway integrationGateway;
    @Override
    public Object createProductByCompany(UUID userId, String loginId, SiteProductCreateInput input) throws JsonProcessingException {
        EntityUserCompany entityUserCompany = getEntityUser(userId);
        SiteProduct siteProduct = (SiteProduct) siteProductCreateService.createSiteProduct(input, loginId);
        CompanySiteProduct companySiteProduct = new CompanySiteProduct();
        companySiteProduct.setCompanyId(entityUserCompany.getCompanyId());
        companySiteProduct.setSiteProductId(siteProduct.getSiteProductId());
        companySiteProductRepository.save(companySiteProduct);
        return companySiteProduct;
    }

    @Override
    public Object getSiteProductList(UUID userId) {
        EntityUserCompany entityUserCompany = getEntityUser(userId);
        return integrationGateway.getListSiteProduct(entityUserCompany.getCompanyId(), BooleanStatus.NO.getCode(), null);
    }

    @Override
    public Object searchSiteProduct(UUID userId, SiteProductSearchInput input) {
        EntityUserCompany entityUserCompany = getEntityUser(userId);
        Page<SiteProductSearchOutput> pageSiteProduct = integrationGateway.
                getSearchSiteProduct(
                        entityUserCompany.getCompanyId(),
                        input.getProductCode(),
                        input.getProductName(),
                        input.getProductTypeId(),
                        PageRequest.of(input.getOffset(), input.getSize())
                );
        ResponsePageDTO output = new ResponsePageDTO<>();
        output.setList(pageSiteProduct.getContent());
        output.setPage(pageSiteProduct.getNumber());
        output.setResultPerPage(pageSiteProduct.getSize());
        output.setTotalResult(pageSiteProduct.getTotalElements());
        return output;
    }
    private EntityUserCompany getEntityUser (UUID userId) {
        EntityUserCompany result = entityUserCompanyRepository.findById(userId).orElse(null);
        if (Objects.isNull(result)){
            throw new HttpStatusException(HttpStatusCode.AUTH_DATA_NOT_FOUND);
        }
        return result;
    }
}
