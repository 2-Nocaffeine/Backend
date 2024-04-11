package com.nocaffeine.ssgclone.category.vo.response;

import com.nocaffeine.ssgclone.category.dto.response.CategoryResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryResponseVo {
    private Long id;
    private String name;

    public CategoryResponseVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<CategoryResponseVo> convertToVo(List<CategoryResponseDto> categoryResponseDtoList) {

        List<CategoryResponseVo> categoryResponseVoList = new ArrayList<>();

        for (CategoryResponseDto categoryResponseDto : categoryResponseDtoList) {
            categoryResponseVoList.add(new CategoryResponseVo(
                categoryResponseDto.getId(),
                categoryResponseDto.getName()
            ));
        }
        return categoryResponseVoList;
    }
}
