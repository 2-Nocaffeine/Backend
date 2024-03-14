package com.nocaffeine.ssgclone.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder // 빌더는 객체를 생성할 때 사용하는데, 생성자를 사용하지 않고 객체를 생성할 수 있다.
public class ProductResponseDto {

    private Long id;
    private String name;
    private int price;
    private String content;
    private int discount;
}
