package com.nocaffeine.ssgclone.category.dto.response;

import com.nocaffeine.ssgclone.brandstore.domain.Brand;
import com.nocaffeine.ssgclone.category.domain.ProductList;
import com.nocaffeine.ssgclone.product.domain.Image;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.domain.Total;
import com.nocaffeine.ssgclone.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListResponse {

    private Long productList_id;
    private String product_name;
    private int product_price;
}
