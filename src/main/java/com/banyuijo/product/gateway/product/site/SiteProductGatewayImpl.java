package com.banyuijo.product.gateway.product.site;

import com.banyuijo.product.dto.product.site.SiteProductListOutput;
import com.banyuijo.product.dto.product.site.SiteProductSearchOutput;
import com.banyuijo.product.entity.QProductType;
import com.banyuijo.product.entity.QSiteProduct;
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
                productType.productTypeCode,
                siteProduct.lastUpdatedDate
        );
        JPAQuery<SiteProductSearchOutput> query = queryFactory.
                select(entity).
                from(siteProduct).
                join(productType).
                on(siteProduct.productTypeId.eq(productType.productTypeId)).
                where(
                        siteProduct.siteProductName.likeIgnoreCase("%"+productName+"%"),
                        siteProduct.siteProductCode.likeIgnoreCase("%"+productCode+"%")
                        ).
                limit(pageable.getPageSize()).
                offset(pageable.getOffset()).
                orderBy(siteProduct.siteProductCode.asc());
        if (Objects.nonNull(productTypeId)){
            query.where(productType.productTypeId.eq(productTypeId));
        }
        List<SiteProductSearchOutput> result = query.fetch();
        long totalCount = query.fetchCount();
        return new PageImpl<>(result, pageable, totalCount);
    }
}
