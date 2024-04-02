package com.nocaffeine.ssgclone.product.dto.response;

import com.nocaffeine.ssgclone.product.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionSelectedProductResponseDto {

    private Long id;
    private Product product;
    private SizeOption sizeOption;
    private ColorOption colorOption;
    private AddOption addOption;
    private int stock;

    public static OptionSelectedProductResponseDto fromOptionSelectedProduct(OptionSelectedProduct optionSelectedProduct) {
        return OptionSelectedProductResponseDto.builder()
                .id(optionSelectedProduct.getId())
                .product(optionSelectedProduct.getProduct())
                .sizeOption(optionSelectedProduct.getSizeOption())
                .colorOption(optionSelectedProduct.getColorOption())
                .addOption(optionSelectedProduct.getAddOption())
                .stock(optionSelectedProduct.getStock())
                .build();
    }
}