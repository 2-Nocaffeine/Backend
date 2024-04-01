package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.domain.*;
import com.nocaffeine.ssgclone.product.dto.OptionSelectedProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OptionSelectedProductResponseVo {

    private Long optionSelectedProductId;
    private Long productId;
    private String productName;
    private int productPrice;
    private int productDiscount;
    private String size;
    private String color;
    private String addOption;
    private int stock;

    public OptionSelectedProductResponseVo(Long id, Product product, SizeOption sizeOption, ColorOption colorOption, AddOption addOption, int stock) {
        this.optionSelectedProductId = id;
        this.productId = product.getId();
        this.productName = product.getName();
        this.productPrice = product.getPrice();
        this.productDiscount = product.getDiscount();
        this.size = sizeOption.getSize();
        this.color = colorOption.getColor();
        this.addOption = addOption.getOptionName();
        this.stock = stock;
    }

    public static OptionSelectedProductResponseVo optionSelectedProductDtoToVo(OptionSelectedProductDto optionSelectedProductDto) {
        return new OptionSelectedProductResponseVo(
                optionSelectedProductDto.getId(),
                optionSelectedProductDto.getProduct(),
                optionSelectedProductDto.getSizeOption(),
                optionSelectedProductDto.getColorOption(),
                optionSelectedProductDto.getAddOption(),
                optionSelectedProductDto.getStock()
        );
    }
}
