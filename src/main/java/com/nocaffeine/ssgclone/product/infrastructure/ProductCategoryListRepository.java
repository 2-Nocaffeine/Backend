package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.category.domain.ProductCategoryList;
import com.nocaffeine.ssgclone.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryListRepository extends JpaRepository<ProductCategoryList,Long> {

    // 대 카테고리 아이디로 상품 카테고리 리스트 조회
    List<ProductCategoryList> findByLargeCategoryId(Long largeId);

    Page<ProductCategoryList> findByLargeCategoryId(Long largeId, Pageable page);

    // 중 카테고리 아이디로 상품 카테고리 리스트 조회
    List<ProductCategoryList> findByMediumCategoryId(Long mediumId);

    Page<ProductCategoryList> findByMediumCategoryId(Long mediumId, Pageable page);

    // 소 카테고리 아이디로 상품 카테고리 리스트 조회
    List<ProductCategoryList> findBySmallCategoryId(Long smallId);

    Page<ProductCategoryList> findBySmallCategoryId(Long smallId, Pageable page);

    // 소소 카테고리 아이디로 상품 카테고리 리스트 조회
    List<ProductCategoryList> findByTinyCategory(Long tinyId);

    Page<ProductCategoryList> findByTinyCategory(Long tinyId, Pageable page);

    // 상품으로 상품 카테고리 리스트 조회
    ProductCategoryList findByProduct(Product product);
}
