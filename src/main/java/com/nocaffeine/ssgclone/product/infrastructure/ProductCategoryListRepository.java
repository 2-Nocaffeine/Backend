package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.category.domain.ProductCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryListRepository extends JpaRepository<ProductCategoryList,Long> {
    List<ProductCategoryList> findByLargeCategoryId(Long largeId);

    List<ProductCategoryList> findByMediumCategoryId(Long mediumId);

    List<ProductCategoryList> findBySmallCategoryId(Long smallId);

    List<ProductCategoryList> findByTinyCategory(Long tinyId);
}
