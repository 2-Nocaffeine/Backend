package com.nocaffeine.ssgclone.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmallCategoryResponse {

    private Long id;
    private String name;
    private Long mediumCategoryId;
}
