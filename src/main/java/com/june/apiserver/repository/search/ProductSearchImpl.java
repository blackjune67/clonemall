package com.june.apiserver.repository.search;

import com.june.apiserver.domain.Product;
import com.june.apiserver.domain.QProduct;
import com.june.apiserver.domain.QProductImage;
import com.june.apiserver.dto.PageRequestDto;
import com.june.apiserver.dto.PageResponseDto;
import com.june.apiserver.dto.ProductDto;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;

@Slf4j
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {
    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public PageResponseDto<ProductDto> searchList(PageRequestDto pageRequestDto) {
        log.info("===== Search List 동작여부 =====");
        Pageable pageable = PageRequest.of(
                pageRequestDto.getPage() - 1,
                pageRequestDto.getSize(),
                Sort.by("pno").descending());

        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;
        JPQLQuery<Product> query = from(product);
        query.leftJoin(product.imageList, productImage);
        query.where(productImage.ord.eq(0));

        Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, query);
        List<Product> productList = query.fetch();
        long count = query.fetchCount();

        log.info("=========================================");
        log.info(productList.toString());
        return null;
    }
}
