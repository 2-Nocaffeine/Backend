package com.nocaffeine.ssgclone.brandstore.vo.response;

import com.nocaffeine.ssgclone.brandstore.dto.response.BrandResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BrandResponseVo {

    private Long brandId;
    private String brandName;


    public BrandResponseVo(Long brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public static BrandResponseVo convertToVo(BrandResponseDto brandResponseDto) {
        return new BrandResponseVo(
                brandResponseDto.getBrandId(),
                brandResponseDto.getBrandName()
        );
    }
}
