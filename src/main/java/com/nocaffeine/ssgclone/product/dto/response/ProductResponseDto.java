package com.nocaffeine.ssgclone.product.dto.response;

import com.nocaffeine.ssgclone.product.domain.Product;
import lombok.*;


@Builder // 빌더는 객체를 생성할 때 사용하는데, 생성자를 사용하지 않고 객체를 생성할 수 있다.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private Long id;
    private String name;
    private int price;
    private String content;
    private int discount;

    public static ProductResponseDto fromProduct(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .content(product.getContent())
                .discount(product.getDiscount())
                .build();
    }
}
