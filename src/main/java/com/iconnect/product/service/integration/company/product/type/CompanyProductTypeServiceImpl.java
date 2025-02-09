package com.iconnect.product.service.integration.company.product.type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iconnect.product.dto.ResponsePageDTO;
import com.iconnect.product.dto.product.type.ProductTypeCreateInput;
import com.iconnect.product.dto.product.type.ProductTypeSearchInput;
import com.iconnect.product.dto.product.type.ProductTypeSearchOutput;
import com.iconnect.product.entity.auth.EntityUserCompany;
import com.iconnect.product.entity.integration.CompanyProductType;
import com.iconnect.product.entity.product.ProductType;
import com.iconnect.product.enums.BooleanStatus;
import com.iconnect.product.enums.HttpStatusCode;
import com.iconnect.product.exception.HttpStatusException;
import com.iconnect.product.gateway.integration.IntegrationGateway;
import com.iconnect.product.repository.auth.EntityUserCompanyRepository;
import com.iconnect.product.repository.integration.CompanyProductTypeRepository;
import com.iconnect.product.service.product.product.type.create.ProductTypeCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CompanyProductTypeServiceImpl implements CompanyProductTypeService {
    private final EntityUserCompanyRepository entityUserCompanyRepository;
    private final CompanyProductTypeRepository companyProductTypeRepository;
    private final ProductTypeCreateService productTypeCreateService;
    private final IntegrationGateway integrationGateway;
    @Override
    public Object createProductTypeByCompany(UUID userId, String loginId, ProductTypeCreateInput input) throws JsonProcessingException {
        EntityUserCompany entityUserCompany = getEntityUser(userId);
        ProductType productType = (ProductType) productTypeCreateService.createProductType(input, loginId);
        CompanyProductType companyProductType = new CompanyProductType();
        companyProductType.setCompanyId(entityUserCompany.getCompanyId());
        companyProductType.setProductTypeId(productType.getProductTypeId());
        companyProductTypeRepository.save(companyProductType);
        return companyProductType;
    }

    @Override
    public Object getProductTypeList(UUID userId) {
        EntityUserCompany entityUserCompany = getEntityUser(userId);
        return integrationGateway.getListProductType(entityUserCompany.getCompanyId(), BooleanStatus.NO.getCode());
    }

    @Override
    public Object searchProductType(UUID userId, ProductTypeSearchInput input) {
        EntityUserCompany entityUserCompany = getEntityUser(userId);
        Page<ProductTypeSearchOutput> outputPage = integrationGateway.
                getSearchProductType(
                        entityUserCompany.getCompanyId(),
                        input.getProductTypeCode(),
                        input.getProductTypeName(),
                        PageRequest.of(input.getOffset(), input.getSize())
                );
        ResponsePageDTO output = new ResponsePageDTO<>();
        output.setList(outputPage.getContent());
        output.setPage(outputPage.getNumber());
        output.setResultPerPage(outputPage.getSize());
        output.setTotalResult(outputPage.getTotalElements());
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
