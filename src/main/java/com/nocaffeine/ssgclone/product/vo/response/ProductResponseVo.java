package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.ProductDto;
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

    public static ProductResponseVo productDtoToVo(ProductDto productDto) {
        return new ProductResponseVo(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getContent(),
                productDto.getDiscount()
        );
    }
}
