package com.nocaffeine.ssgclone.product.dto;

import com.nocaffeine.ssgclone.product.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionSelectedProductDto {

    private Product product;
    private SizeOption sizeOption;
    private ColorOption colorOption;
    private AddOption addOption;
    private int stock;

    public static OptionSelectedProductDto fromOptionSelectedProduct(OptionSelectedProduct optionSelectedProduct) {
        return OptionSelectedProductDto.builder()
                .product(optionSelectedProduct.getProduct())
                .sizeOption(optionSelectedProduct.getSizeOption())
                .colorOption(optionSelectedProduct.getColorOption())
                .addOption(optionSelectedProduct.getAddOption())
                .stock(optionSelectedProduct.getStock())
                .build();
    }
}
