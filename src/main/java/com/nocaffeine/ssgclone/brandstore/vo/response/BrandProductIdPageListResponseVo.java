package com.nocaffeine.ssgclone.brandstore.vo.response;

import com.nocaffeine.ssgclone.brandstore.dto.response.BrandProductIdPageListResponseDto;
import com.nocaffeine.ssgclone.brandstore.dto.response.BrandProductIdResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BrandProductIdPageListResponseVo {

    private boolean next;
    private boolean last;
    private List<BrandProductIdResponseVo> brandProductIdList;

    public BrandProductIdPageListResponseVo(boolean next, boolean last, List<BrandProductIdResponseDto> brandProductIdList) {
        this.next = next;
        this.last = last;
        this.brandProductIdList = BrandProductIdResponseVo.convertToVo(brandProductIdList);
    }

    public static BrandProductIdPageListResponseVo convertToVo(BrandProductIdPageListResponseDto brandProductIdPageListResponseDto) {
        return new BrandProductIdPageListResponseVo(
                brandProductIdPageListResponseDto.isNext(),
                brandProductIdPageListResponseDto.isLast(),
                brandProductIdPageListResponseDto.getBrandProductIdList()
        );
    }
}
