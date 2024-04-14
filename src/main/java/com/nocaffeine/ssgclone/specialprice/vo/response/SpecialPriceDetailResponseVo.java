package com.nocaffeine.ssgclone.specialprice.vo.response;

import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceDetailResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpecialPriceDetailResponseVo {

    private String specialPriceName;
    private int lowestPrice;
    private String thumbnailUrl;
    private List<SpecialPriceProductIdResponseVo> specialPriceProductList;


    public SpecialPriceDetailResponseVo(String specialPriceName, int lowestPrice, String thumbnailUrl, List<SpecialPriceProductIdResponseVo> specialPriceProductList) {
        this.specialPriceName = specialPriceName;
        this.lowestPrice = lowestPrice;
        this.thumbnailUrl = thumbnailUrl;
        this.specialPriceProductList = specialPriceProductList;
    }

    public static SpecialPriceDetailResponseVo convertToVo(SpecialPriceDetailResponseDto specialPriceProductList) {

        return new SpecialPriceDetailResponseVo(
                specialPriceProductList.getSpecialPriceName(),
                specialPriceProductList.getLowestPrice(),
                specialPriceProductList.getThumbnailUrl(),
                SpecialPriceProductIdResponseVo.convertToVo(specialPriceProductList.getSpecialPriceProductList())
                );
    }
}
