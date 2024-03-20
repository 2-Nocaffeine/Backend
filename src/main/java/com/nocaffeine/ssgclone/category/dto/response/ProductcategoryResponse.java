package com.nocaffeine.ssgclone.category.dto.response;

import com.nocaffeine.ssgclone.category.domain.LargeCategory;
import com.nocaffeine.ssgclone.category.domain.MediumCategory;
import com.nocaffeine.ssgclone.category.domain.SmallCategory;
import com.nocaffeine.ssgclone.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductcategoryResponse {

    private long id;
    private Product product;
    private LargeCategory largeCategory;
    private MediumCategory mediumCategory;
    private SmallCategory smallCategory;
    private Long tinyCategory;
}
