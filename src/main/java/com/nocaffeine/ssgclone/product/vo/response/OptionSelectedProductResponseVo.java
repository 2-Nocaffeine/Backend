package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.domain.*;
import com.nocaffeine.ssgclone.product.dto.OptionSelectedProductDto;
import lombok.Getter;

@Getter
public class OptionSelectedProductResponseVo {

    private final Long optionSelectedProductId;
    private final Long productId;
    private final String productName;
    private final int productPrice;
    private final int productDiscount;
    private final String size;
    private final String color;
    private final String addOption;
    private final int stock;

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
