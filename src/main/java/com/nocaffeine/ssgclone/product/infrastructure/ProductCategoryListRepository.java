package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.category.domain.ProductCategoryList;
import com.nocaffeine.ssgclone.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryListRepository extends JpaRepository<ProductCategoryList,Long> {
    List<ProductCategoryList> findByLargeCategoryId(Long largeId);

    List<ProductCategoryList> findByMediumCategoryId(Long mediumId);

    List<ProductCategoryList> findBySmallCategoryId(Long smallId);

    List<ProductCategoryList> findByTinyCategory(Long tinyId);

    ProductCategoryList findByProduct(Product product);
}
