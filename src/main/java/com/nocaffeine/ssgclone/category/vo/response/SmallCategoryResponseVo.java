package com.nocaffeine.ssgclone.category.vo.response;

import com.nocaffeine.ssgclone.category.dto.response.SmallCategoryResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmallCategoryResponseVo {

    private Long smallCategoryId;
    private String smallCategoryName;

    public SmallCategoryResponseVo(Long smallCategoryId, String smallCategoryName) {
        this.smallCategoryId = smallCategoryId;
        this.smallCategoryName = smallCategoryName;
    }

    public static List<SmallCategoryResponseVo> convertToVo(List<SmallCategoryResponseDto> smallCategoryResponseDtoList) {

        List<SmallCategoryResponseVo> smallCategoryResponseVoList = new ArrayList<>();

        for (SmallCategoryResponseDto smallCategoryResponseDto : smallCategoryResponseDtoList) {
                 smallCategoryResponseVoList.add(new SmallCategoryResponseVo(
                         smallCategoryResponseDto.getSmallCategoryId(),
                         smallCategoryResponseDto.getSmallCategoryName()
            ));

        }
        return smallCategoryResponseVoList;
    }
}
