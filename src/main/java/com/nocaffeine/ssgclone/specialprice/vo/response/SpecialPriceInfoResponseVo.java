package com.nocaffeine.ssgclone.specialprice.vo.response;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceInfoResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class SpecialPriceInfoResponseVo {

    private String title;
    private int lowestPrice;
    private String thumbnailUrl;
    private String subTitle;


    private SpecialPriceInfoResponseVo(String title, int lowestPrice, String thumbnailUrl, String subTitle) {
        this.title = title;
        this.lowestPrice = lowestPrice;
        this.thumbnailUrl = thumbnailUrl;
        this.subTitle = subTitle;
    }

    public static SpecialPriceInfoResponseVo convertToVo(SpecialPriceInfoResponseDto specialPriceInfoResponseDto) {
        return new SpecialPriceInfoResponseVo(
                specialPriceInfoResponseDto.getTitle(),
                specialPriceInfoResponseDto.getLowestPrice(),
                specialPriceInfoResponseDto.getThumbnailUrl(),
                specialPriceInfoResponseDto.getSubTitle()
        );
    }
}
