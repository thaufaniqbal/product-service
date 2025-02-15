package com.iconnect.product.gateway.integration;

import com.iconnect.product.dto.integration.IntCompanyCustomerSearchInput;
import com.iconnect.product.dto.integration.IntCompanyCustomerSearchOutput;
import com.iconnect.product.dto.product.site.product.SiteProductListOutput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchOutput;
import com.iconnect.product.dto.product.type.ProductTypeListOutput;
import com.iconnect.product.dto.product.type.ProductTypeSearchOutput;
import com.iconnect.product.entity.customer.QCustomer;
import com.iconnect.product.entity.integration.QCompanyCustomer;
import com.iconnect.product.entity.integration.QCompanyProductType;
import com.iconnect.product.entity.integration.QCompanySiteProduct;
import com.iconnect.product.entity.product.QProductType;
import com.iconnect.product.entity.product.QSiteProduct;
import com.iconnect.product.enums.BooleanStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class IntegrationGatewayImpl implements IntegrationGateway {
    private final EntityManager entityManager;
    @Override
    public List<SiteProductListOutput> getListSiteProduct(UUID companyId, Integer deleteStatus, UUID productTypeId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
        QProductType productType = QProductType.productType;
        QSiteProduct siteProduct = QSiteProduct.siteProduct;
        QCompanySiteProduct companySiteProduct = QCompanySiteProduct.companySiteProduct;

        QBean<SiteProductListOutput> entity = Projections.fields(SiteProductListOutput.class,
                siteProduct.siteProductId,
                siteProduct.siteProductName,
                siteProduct.siteProductCode
        );
        JPAQuery<SiteProductListOutput> query = queryFactory.
                select(entity).
                from(siteProduct).
                join(productType).
                on(siteProduct.productTypeId.eq(productType.productTypeId)).
                join(companySiteProduct).
                on(companySiteProduct.siteProductId.eq(siteProduct.siteProductId)).
                orderBy(siteProduct.siteProductCode.asc());
        query.where(siteProduct.deleteStatus.eq(deleteStatus));
        if (Objects.nonNull(productTypeId)){
            query.where(productType.productTypeId.eq(productTypeId));
        }
        if (Objects.nonNull(companyId)){
            query.where(companySiteProduct.companyId.eq(companyId));
        }
        return query.fetch();
    }

    @Override
    public Page<SiteProductSearchOutput> getSearchSiteProduct(UUID companyId, String productCode, String productName, UUID productTypeId, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
        QProductType productType = QProductType.productType;
        QSiteProduct siteProduct = QSiteProduct.siteProduct;
        QCompanySiteProduct companySiteProduct = QCompanySiteProduct.companySiteProduct;

        QBean<SiteProductSearchOutput> entity = Projections.fields(SiteProductSearchOutput.class,
                siteProduct.siteProductId,
                siteProduct.siteProductName,
                siteProduct.siteProductCode,
                productType.productTypeId,
                productType.productTypeName,
                siteProduct.lastUpdatedDate
        );
        JPAQuery<SiteProductSearchOutput> query = queryFactory.
                select(entity).
                from(siteProduct).
                join(productType).
                on(siteProduct.productTypeId.eq(productType.productTypeId)).
                join(companySiteProduct).
                on(companySiteProduct.siteProductId.eq(siteProduct.siteProductId)).
                where(siteProduct.deleteStatus.eq(BooleanStatus.NO.getCode())).
                limit(pageable.getPageSize()).
                offset(pageable.getOffset()).
                orderBy(siteProduct.siteProductCode.asc());
        if (Objects.nonNull(productTypeId)){
            query.where(productType.productTypeId.eq(productTypeId));
        }
        if (productCode != null) {
            query.where(siteProduct.siteProductCode.likeIgnoreCase("%"+productCode.toLowerCase()+"%"));
        }
        if (productName != null) {
            query.where(siteProduct.siteProductName.likeIgnoreCase("%"+productName.toLowerCase()+"%"));
        }
        if (Objects.nonNull(companyId)){
            query.where(companySiteProduct.companyId.eq(companyId));
        }
        List<SiteProductSearchOutput> result = query.fetch();
        long totalCount = query.fetchCount();
        return new PageImpl<>(result, pageable, totalCount);
    }

    @Override
    public List<ProductTypeListOutput> getListProductType(UUID companyId, Integer deleteStatus) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
        QProductType productType = QProductType.productType;
        QCompanyProductType companyProductType = QCompanyProductType.companyProductType;

        QBean<ProductTypeListOutput> entity = Projections.fields(ProductTypeListOutput.class,
                productType.productTypeId,
                productType.productTypeName,
                productType.productTypeCode
        );
        JPAQuery<ProductTypeListOutput> query = queryFactory.
                select(entity).
                from(productType).
                join(companyProductType).
                on(companyProductType.productTypeId.eq(productType.productTypeId)).
                where(productType.deleteStatus.eq(deleteStatus)).
                orderBy(productType.productTypeCode.asc());
        if (Objects.nonNull(companyId)){
            query.where(companyProductType.companyId.eq(companyId));
        }
        return query.fetch();
    }

    @Override
    public Page<ProductTypeSearchOutput> getSearchProductType(UUID companyId, String productTypeCode, String productName, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
        QProductType productType = QProductType.productType;
        QCompanyProductType companyProductType = QCompanyProductType.companyProductType;

        QBean<ProductTypeSearchOutput> entity = Projections.fields(ProductTypeSearchOutput.class,
                productType.productTypeId,
                productType.productTypeCode,
                productType.productTypeName,
                productType.lastUpdatedDate
        );
        JPAQuery<ProductTypeSearchOutput> query = queryFactory.
                select(entity).
                from(productType).
                join(companyProductType).
                on(companyProductType.productTypeId.eq(productType.productTypeId)).
                where(
                        productType.deleteStatus.eq(BooleanStatus.NO.getCode())
                ).
                limit(pageable.getPageSize()).
                offset(pageable.getOffset()).
                orderBy(productType.productTypeCode.asc());
        query.where(productType.deleteStatus.eq(BooleanStatus.NO.getCode()));
        if (productTypeCode != null) {
            query.where(productType.productTypeCode.likeIgnoreCase("%"+productTypeCode.toLowerCase()+"%"));
        }
        if (productName != null) {
            query.where(productType.productTypeName.likeIgnoreCase("%"+productName.toLowerCase()+"%"));
        }
        if (Objects.nonNull(companyId)){
            query.where(companyProductType.companyId.eq(companyId));
        }
        List<ProductTypeSearchOutput> result = query.fetch();
        long totalCount = query.fetchCount();
        return new PageImpl<>(result, pageable, totalCount);
    }

    @Override
    public Page<IntCompanyCustomerSearchOutput> getSearchCustomer(UUID companyId, IntCompanyCustomerSearchInput input, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
        QCompanyCustomer companyCustomer = QCompanyCustomer.companyCustomer;
        QCustomer customer = QCustomer.customer;

        QBean<IntCompanyCustomerSearchOutput> entity = Projections.fields(IntCompanyCustomerSearchOutput.class,
                customer.customerId,
                customer.customerName
        );
        JPAQuery<IntCompanyCustomerSearchOutput> query = queryFactory.
                select(entity).
                from(companyCustomer).
                join(customer).
                on(customer.customerId.eq(companyCustomer.customerId)).
                where(
                        companyCustomer.companyId.eq(companyId)
                ).
                limit(pageable.getPageSize()).
                offset(pageable.getOffset()).
                orderBy(customer.customerName.asc());

        if (Objects.nonNull(input.getCustomerName()) && !input.getCustomerName().isEmpty()){
            query.where(customer.customerName.likeIgnoreCase("%" + input.getCustomerName().toLowerCase() + "%"));
        }

        List<IntCompanyCustomerSearchOutput> result = query.fetch();
        long totalCount = query.fetchCount();
        return new PageImpl<>(result, pageable, totalCount);
    }

}
