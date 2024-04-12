package com.nocaffeine.ssgclone.specialprice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialPriceProductPageListResponseDto {

    private boolean next;
    private boolean last;
    List<SpecialPriceProductIdResponseDto> specialPriceProductIdResponseDtoList;

    public static SpecialPriceProductPageListResponseDto fromSpecialPriceProductPageListResponseDto(
            boolean next, boolean last, List<SpecialPriceProductIdResponseDto> specialPriceProductIdResponseDtoList) {
        return SpecialPriceProductPageListResponseDto.builder()
                .next(next)
                .last(last)
                .specialPriceProductIdResponseDtoList(specialPriceProductIdResponseDtoList)
                .build();
    }
}
