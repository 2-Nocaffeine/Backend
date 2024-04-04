package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.response.OptionSelectedProductResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class OptionSelectedProductListResponseVo {

    private Long optionSelectedProductId;
    private Long colorOptionId;
    private String color;
    private Long sizeOptionId;
    private String size;
    private Long addOptionId;
    private String addOption;
    private int stock;

    public OptionSelectedProductListResponseVo(Long optionSelectedProductId, Long colorOptionId, String color, Long sizeOptionId, String size, Long addOptionId, String addOption, int stock) {
        this.optionSelectedProductId = optionSelectedProductId;
        this.colorOptionId = colorOptionId;
        this.color = color;
        this.sizeOptionId = sizeOptionId;
        this.size = size;
        this.addOptionId = addOptionId;
        this.addOption = addOption;
        this.stock = stock;
    }

    public static List<OptionSelectedProductListResponseVo> optionSelectedProductDtoToVo(List<OptionSelectedProductResponseDto> optionSelectedProductResponseDto) {

        List<OptionSelectedProductListResponseVo> optionSelectedProductListResponseVo = new ArrayList<>();

        for (OptionSelectedProductResponseDto optionSelectedProductResponseDtos : optionSelectedProductResponseDto) {
            optionSelectedProductListResponseVo.add(new OptionSelectedProductListResponseVo(
                       optionSelectedProductResponseDtos.getId(),
                        optionSelectedProductResponseDtos.getColorOption().getId(),
                        optionSelectedProductResponseDtos.getColorOption().getColor(),
                        optionSelectedProductResponseDtos.getSizeOption().getId(),
                        optionSelectedProductResponseDtos.getSizeOption().getSize(),
                        optionSelectedProductResponseDtos.getAddOption().getId(),
                        optionSelectedProductResponseDtos.getAddOption().getOptionName(),
                        optionSelectedProductResponseDtos.getStock()
            ));
        }

        return optionSelectedProductListResponseVo;
    }
}
