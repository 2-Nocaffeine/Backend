package com.nocaffeine.ssgclone.specialprice.vo.response;
import
        com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceProductIdResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class SpecialPriceProductIdResponseVo {

    private Long productId;


    private SpecialPriceProductIdResponseVo(Long productId) {
        this.productId = productId;
    }

    public static List<SpecialPriceProductIdResponseVo> convertToVo(List<SpecialPriceProductIdResponseDto> specialPriceProductList) {

        List<SpecialPriceProductIdResponseVo> specialPriceProductIdResponseVos = new ArrayList<>();

        for (SpecialPriceProductIdResponseDto specialPriceProduct : specialPriceProductList){
            specialPriceProductIdResponseVos.add(new SpecialPriceProductIdResponseVo( specialPriceProduct.getProductId()));
        }
        return specialPriceProductIdResponseVos;
    }
}
