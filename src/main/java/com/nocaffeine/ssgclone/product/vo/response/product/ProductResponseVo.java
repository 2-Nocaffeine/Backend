package com.nocaffeine.ssgclone.product.vo.response.product;

import com.nocaffeine.ssgclone.product.dto.response.product.ProductResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseVo {

    private Long id;
    private String name;
    private int price;
    private String content;
    private int discount;

    public ProductResponseVo(Long id, String name, int price, String content, int discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.content = content;
        this.discount = discount;
    }

    public static ProductResponseVo productDtoToVo(ProductResponseDto productResponseDto) {
        return new ProductResponseVo(
                productResponseDto.getId(),
                productResponseDto.getName(),
                productResponseDto.getPrice(),
                productResponseDto.getContent(),
                productResponseDto.getDiscount()
        );
    }
}
