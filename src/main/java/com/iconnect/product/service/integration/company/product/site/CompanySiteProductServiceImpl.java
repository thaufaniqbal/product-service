package com.iconnect.product.service.integration.company.product.site;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.ResponsePageDTO;
import com.iconnect.product.dto.product.site.product.SiteProductCreateInput;
import com.iconnect.product.dto.product.site.product.SiteProductListOutput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchInput;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.integration.CompanySiteProduct;
import com.iconnect.product.entity.product.SiteProduct;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.repository.auth.EntityUserCompanyRepository;
import com.iconnect.product.repository.integration.CompanySiteProductRepository;
import com.iconnect.product.service.product.product.site.product.create.SiteProductCreateService;
import lombok.RequiredArgsConstructor;
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
        List<SiteProductListOutput> results = new ArrayList<>();
        EntityUserCompany entityUserCompany = getEntityUser(userId);
        List<CompanySiteProduct> companySiteProducts = companySiteProductRepository.findAllByCompanyId(entityUserCompany.getCompanyId());
        for (var companySiteProduct : companySiteProducts){
            SiteProductListOutput result = new SiteProductListOutput();
            result.setSiteProductId(companySiteProduct.getSiteProductId());
            result.setSiteProductName(null);
            result.setSiteProductCode(null);
            results.add(result);
        }
        return results;
    }

    @Override
    public Object searchSiteProduct(UUID userId, SiteProductSearchInput input) {
        List<SiteProductListOutput> results = new ArrayList<>();
        EntityUserCompany entityUserCompany = getEntityUser(userId);
        List<CompanySiteProduct> companySiteProducts = companySiteProductRepository.findAllByCompanyId(entityUserCompany.getCompanyId());
        for (var companySiteProduct : companySiteProducts){
            SiteProductListOutput result = new SiteProductListOutput();
            result.setSiteProductId(companySiteProduct.getSiteProductId());
            result.setSiteProductName(null);
            result.setSiteProductCode(null);
            results.add(result);
        }
        ResponsePageDTO output = new ResponsePageDTO<>();
        output.setList(results);
        output.setPage(0);
        output.setResultPerPage(0);
        output.setTotalResult(0l);
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
