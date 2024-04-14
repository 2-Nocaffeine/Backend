package com.nocaffeine.ssgclone.brandstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandProductIdPageListResponseDto {

    private boolean next;
    private boolean last;
    private List<BrandProductIdResponseDto> brandProductIdList;

    public static BrandProductIdPageListResponseDto fromBrandProductIdPageListResponseDto(boolean next, boolean last, List<BrandProductIdResponseDto> brandProductIdList) {
        return BrandProductIdPageListResponseDto.builder()
                .next(next)
                .last(last)
                .brandProductIdList(brandProductIdList)
                .build();
    }
}
