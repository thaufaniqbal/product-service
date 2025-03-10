package com.iconnect.product.gateway.product.site;

import com.iconnect.product.dto.product.site.product.SiteProductListOutput;
import com.iconnect.product.dto.product.site.product.SiteProductSearchOutput;

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
public class SiteProductGatewayImpl implements SiteProductGateway {
    private final EntityManager entityManager;
    @Override
    public List<SiteProductListOutput> getListByDeleteStatusAndProductTypeId(int deleteStatus, UUID productTypeId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
        QProductType productType = QProductType.productType;
        QSiteProduct siteProduct = QSiteProduct.siteProduct;

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
                orderBy(siteProduct.siteProductCode.asc());
        query.where(siteProduct.deleteStatus.eq(deleteStatus));
        if (Objects.nonNull(productTypeId)){
            query.where(productType.productTypeId.eq(productTypeId));
        }
        return query.fetch();
    }

    @Override
    public Page<SiteProductSearchOutput> getSearchSiteProduct(String productCode, String productName, UUID productTypeId, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
        QProductType productType = QProductType.productType;
        QSiteProduct siteProduct = QSiteProduct.siteProduct;

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
        List<SiteProductSearchOutput> result = query.fetch();
        long totalCount = query.fetchCount();
        return new PageImpl<>(result, pageable, totalCount);
    }
}
