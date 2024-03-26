package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.domain.AddOption;
import com.nocaffeine.ssgclone.product.domain.ColorOption;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.domain.SizeOption;
import com.nocaffeine.ssgclone.product.dto.OptionSelectedProductDto;
import lombok.Getter;

@Getter
public class OptionSelectedProductResponseVo {

    private final Product product;
    private final SizeOption sizeOption;
    private final ColorOption colorOption;
    private final AddOption addOption;
    private final int stock;

    public OptionSelectedProductResponseVo(Product product, SizeOption sizeOption, ColorOption colorOption, AddOption addOption, int stock) {
        this.product = product;
        this.sizeOption = sizeOption;
        this.colorOption = colorOption;
        this.addOption = addOption;
        this.stock = stock;
    }

    public static OptionSelectedProductResponseVo optionSelectedProductDtoToVo(OptionSelectedProductDto optionSelectedProductDto) {
        return new OptionSelectedProductResponseVo(
                optionSelectedProductDto.getProduct(),
                optionSelectedProductDto.getSizeOption(),
                optionSelectedProductDto.getColorOption(),
                optionSelectedProductDto.getAddOption(),
                optionSelectedProductDto.getStock()
        );
    }
}
