package com.nocaffeine.ssgclone.brandstore.vo.response;

import com.nocaffeine.ssgclone.brandstore.dto.response.BrandProductIdResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandProductIdResponseVo {

    private Long productId;


    public BrandProductIdResponseVo(Long productId) {
        this.productId = productId;
    }
    public static List<BrandProductIdResponseVo> convertToVo(List<BrandProductIdResponseDto> brandProductList) {

        List<BrandProductIdResponseVo> brandProductVoList = new ArrayList<>();

        for (BrandProductIdResponseDto brandProduct : brandProductList) {
            brandProductVoList.add(new BrandProductIdResponseVo(brandProduct.getProductId()));
        }
        return brandProductVoList;
    }
}
