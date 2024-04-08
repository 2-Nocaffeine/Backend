package com.nocaffeine.ssgclone.specialprice.dto.response;

import com.nocaffeine.ssgclone.specialprice.vo.response.SpecialPriceProductIdResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialPriceDetailResponseDto {

    private String specialPriceName;
    private int lowestPrice;
    private String thumbnailUrl;
    private List<SpecialPriceProductIdResponseDto> specialPriceProductList;
}
