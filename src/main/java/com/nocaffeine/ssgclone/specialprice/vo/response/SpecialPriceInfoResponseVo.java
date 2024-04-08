package com.nocaffeine.ssgclone.specialprice.vo.response;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceInfoResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class SpecialPriceInfoResponseVo {

    private int lowestPrice;
    private String thumbnailUrl;


    private SpecialPriceInfoResponseVo(int lowestPrice, String thumbnailUrl) {
        this.lowestPrice = lowestPrice;
        this.thumbnailUrl = thumbnailUrl;
    }

    public static SpecialPriceInfoResponseVo convertToVo(SpecialPriceInfoResponseDto specialPriceInfoResponseDto) {
        return new SpecialPriceInfoResponseVo(
                specialPriceInfoResponseDto.getLowestPrice(),
                specialPriceInfoResponseDto.getThumbnailUrl()
        );
    }
}
