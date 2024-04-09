package com.nocaffeine.ssgclone.category.vo.response;

import com.nocaffeine.ssgclone.category.dto.response.TinyCategoryResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TinyCategoryResponseVo {

    private Long tinyCategoryId;
    private String tinyCategoryName;

    public TinyCategoryResponseVo(Long tinyCategoryId, String tinyCategoryName) {
        this.tinyCategoryId = tinyCategoryId;
        this.tinyCategoryName = tinyCategoryName;
    }

    public static List<TinyCategoryResponseVo> convertToVo(List<TinyCategoryResponseDto> tinyCategoryResponseDtoList) {

        List<TinyCategoryResponseVo> tinyCategoryResponseVoList = new ArrayList<>();

        for (TinyCategoryResponseDto tinyCategoryResponseDto : tinyCategoryResponseDtoList) {
                 tinyCategoryResponseVoList.add(new TinyCategoryResponseVo(
                         tinyCategoryResponseDto.getTinyCategoryId(),
                         tinyCategoryResponseDto.getTinyCategoryName()
            ));

        }
        return tinyCategoryResponseVoList;
    }
}
