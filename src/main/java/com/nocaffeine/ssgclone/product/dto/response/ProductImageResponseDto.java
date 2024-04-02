package com.nocaffeine.ssgclone.product.dto.response;

import com.nocaffeine.ssgclone.product.domain.Image;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.domain.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageResponseDto {

    private Long id;
    private Image image;
    private Product product;

    public static ProductImageResponseDto fromProductImage(ProductImage productImage) {
        return ProductImageResponseDto.builder()
                .id(productImage.getId())
                .image(productImage.getImage())
                .product(productImage.getProduct())
                .build();
    }
}
