package com.banyuijo.product.gateway.product.type;

import com.banyuijo.product.dto.product.type.ProductTypeListOutput;
import com.banyuijo.product.dto.product.type.ProductTypeSearchOutput;
import com.banyuijo.product.entity.QProductType;
import com.banyuijo.product.enums.BooleanStatus;
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

@Component
@RequiredArgsConstructor
public class ProductTypeGatewayImpl implements ProductTypeGateway {
    private final EntityManager entityManager;
    @Override
    public List<ProductTypeListOutput> findAllByDeleteStatus(int deleteStatus) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
        QProductType productType = QProductType.productType;

        QBean<ProductTypeListOutput> entity = Projections.fields(ProductTypeListOutput.class,
                productType.productTypeId,
                productType.productTypeName,
                productType.productTypeCode
        );
        JPAQuery<ProductTypeListOutput> query = queryFactory.
                select(entity).
                from(productType).
                where(productType.deleteStatus.eq(deleteStatus)).
                orderBy(productType.productTypeCode.asc());
        return query.fetch();
    }

    @Override
    public Page<ProductTypeSearchOutput> getSearchSiteProduct(String productCode, String productName, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
        QProductType productType = QProductType.productType;

        QBean<ProductTypeSearchOutput> entity = Projections.fields(ProductTypeSearchOutput.class,
                productType.productTypeId,
                productType.productTypeCode,
                productType.productTypeName,
                productType.lastUpdatedDate
        );
        JPAQuery<ProductTypeSearchOutput> query = queryFactory.
                select(entity).
                from(productType).
                where(
                        productType.deleteStatus.eq(BooleanStatus.NO.getCode())
                ).
                limit(pageable.getPageSize()).
                offset(pageable.getOffset()).
                orderBy(productType.productTypeCode.asc());
        List<ProductTypeSearchOutput> result = query.fetch();

        if (productCode != null) {
            query.where(productType.productTypeName.likeIgnoreCase("%"+productCode.toLowerCase()+"%"));
        }
        if (productName != null) {
            query.where(productType.productTypeName.likeIgnoreCase("%"+productName.toLowerCase()+"%"));
        }

        long totalCount = query.fetchCount();
        return new PageImpl<>(result, pageable, totalCount);
    }
}
