package com.nocaffeine.ssgclone.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmallCategoryResponseDto {

    private Long smallCategoryId;
    private String smallCategoryName;
}
