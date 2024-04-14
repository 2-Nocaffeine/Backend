package com.nocaffeine.ssgclone.specialprice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialPriceInfoResponseDto {

    private String title;
    private int lowestPrice;
    private String thumbnailUrl;
    private String subTitle;
}
