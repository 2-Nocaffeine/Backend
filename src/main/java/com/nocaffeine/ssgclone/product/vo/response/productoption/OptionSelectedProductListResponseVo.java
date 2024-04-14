package com.nocaffeine.ssgclone.product.vo.response.productoption;

import com.nocaffeine.ssgclone.product.dto.response.productoption.OptionSelectedProductResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OptionSelectedProductListResponseVo {

    private Long optionSelectedProductId;
    private Long productId;
    private String productName;
    private int productPrice;
    private int productDiscount;
    private Long colorOptionId;
    private String color;
    private Long sizeOptionId;
    private String size;
    private Long addOptionId;
    private String addOption;
    private int stock;

    public OptionSelectedProductListResponseVo(Long optionSelectedProductId, Long productId, String productName, int productPrice, int productDiscount, Long colorOptionId, String color, Long sizeOptionId, String size, Long addOptionId, String addOption, int stock) {
        this.optionSelectedProductId = optionSelectedProductId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.colorOptionId = colorOptionId;
        this.color = color;
        this.sizeOptionId = sizeOptionId;
        this.size = size;
        this.addOptionId = addOptionId;
        this.addOption = addOption;
        this.stock = stock;
    }

    public static List<OptionSelectedProductListResponseVo> optionSelectedProductDtoListToVoList(List<OptionSelectedProductResponseDto> optionSelectedProductResponseDtoList) {

        List<OptionSelectedProductListResponseVo> optionSelectedProductListResponseVoList = new ArrayList<>();

        for (OptionSelectedProductResponseDto optionSelectedProductResponseDto : optionSelectedProductResponseDtoList) {

            optionSelectedProductListResponseVoList.add(
                    new OptionSelectedProductListResponseVo(
                            optionSelectedProductResponseDto.getId(),
                            optionSelectedProductResponseDto.getProduct().getId(),
                            optionSelectedProductResponseDto.getProduct().getName(),
                            optionSelectedProductResponseDto.getProduct().getPrice(),
                            optionSelectedProductResponseDto.getProduct().getDiscount(),
                            optionSelectedProductResponseDto.getColorOption().getId(),
                            optionSelectedProductResponseDto.getColorOption().getColor(),
                            optionSelectedProductResponseDto.getSizeOption().getId(),
                            optionSelectedProductResponseDto.getSizeOption().getSize(),
                            optionSelectedProductResponseDto.getAddOption().getId(),
                            optionSelectedProductResponseDto.getAddOption().getOptionName(),
                            optionSelectedProductResponseDto.getStock()
                    )
            );
        }

        return optionSelectedProductListResponseVoList;
    }
}
