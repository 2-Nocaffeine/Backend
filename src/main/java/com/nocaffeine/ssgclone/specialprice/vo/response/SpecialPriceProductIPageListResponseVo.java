package com.nocaffeine.ssgclone.specialprice.vo.response;

import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceProductIdResponseDto;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceProductPageListResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SpecialPriceProductIPageListResponseVo {

    private boolean next;
    private boolean last;
    private List<SpecialPriceProductIdResponseVo> specialPriceProductIdList;

    public SpecialPriceProductIPageListResponseVo(
            boolean next,
            boolean last,
            List<SpecialPriceProductIdResponseDto> specialPriceProductIdResponseDtoList) {

        this.next = next;
        this.last = last;

        List<SpecialPriceProductIdResponseVo> specialPriceProductIdResponseVoList =
                SpecialPriceProductIdResponseVo.convertToVo(specialPriceProductIdResponseDtoList);

        Collections.shuffle(specialPriceProductIdResponseVoList);

        this.specialPriceProductIdList = specialPriceProductIdResponseVoList;
    }

    public static SpecialPriceProductIPageListResponseVo fromDtoToVo(
            SpecialPriceProductPageListResponseDto specialPriceProductPageListResponseDto) {
        return new SpecialPriceProductIPageListResponseVo(
                specialPriceProductPageListResponseDto.isNext(),
                specialPriceProductPageListResponseDto.isLast(),
                specialPriceProductPageListResponseDto.getSpecialPriceProductIdResponseDtoList()
        );
    }
}
