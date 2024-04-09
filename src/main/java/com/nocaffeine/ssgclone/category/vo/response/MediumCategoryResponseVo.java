package com.nocaffeine.ssgclone.category.vo.response;

import com.nocaffeine.ssgclone.category.dto.response.MediumCategoryResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MediumCategoryResponseVo {

    private Long mediumCategoryId;
    private String mediumCategoryName;

    public MediumCategoryResponseVo(Long mediumCategoryId, String mediumCategoryName) {
        this.mediumCategoryId = mediumCategoryId;
        this.mediumCategoryName = mediumCategoryName;
    }

    public static List<MediumCategoryResponseVo> convertToVo(List<MediumCategoryResponseDto> MediumCategoryResponseDtoList) {

        List<MediumCategoryResponseVo> mediumCategoryResponseVoList = new ArrayList<>();

        for (MediumCategoryResponseDto mediumCategoryResponseDto : MediumCategoryResponseDtoList) {
            mediumCategoryResponseVoList.add(new MediumCategoryResponseVo(
                    mediumCategoryResponseDto.getMediumCategoryId(),
                    mediumCategoryResponseDto.getMediumCategoryName()
            ));
        }
        return mediumCategoryResponseVoList;
    }
}
