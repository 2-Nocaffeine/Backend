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

//    private ProductList productList_id;
//    private Product product_name;
//    private Product product_price;
//    private Brand brand;
//    private Total rate;
//    private Total reviewCount;
//    private Image url;
    private Long productList_id;
    private String product_name;
    private int product_price;
    private String brand;
    private double rate;
    private int reviewCount;
    private String url;
}
