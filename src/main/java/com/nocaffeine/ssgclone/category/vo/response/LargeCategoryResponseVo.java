package com.nocaffeine.ssgclone.category.vo.response;

import com.nocaffeine.ssgclone.category.dto.response.LargeCategoryResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LargeCategoryResponseVo {

    private Long largeCategoryId;
    private String largeCategoryName;
    private String imageUrl;

    public LargeCategoryResponseVo(Long largeCategoryId, String largeCategoryName, String imageUrl) {
        this.largeCategoryId = largeCategoryId;
        this.largeCategoryName = largeCategoryName;
        this.imageUrl = imageUrl;
    }

    public static List<LargeCategoryResponseVo> convertToVo(List<LargeCategoryResponseDto> largeCategoryResponseDtoList) {

        List<LargeCategoryResponseVo> largeCategoryResponseVoList = new ArrayList<>();

        for (LargeCategoryResponseDto largeCategoryResponseDto : largeCategoryResponseDtoList) {
            largeCategoryResponseVoList.add(new LargeCategoryResponseVo(
                    largeCategoryResponseDto.getLargeCategoryId(),
                    largeCategoryResponseDto.getLargeCategoryName(),
                    largeCategoryResponseDto.getImageUrl()
            ));
        }
        return largeCategoryResponseVoList;
    }
}
