package com.nocaffeine.ssgclone.category.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmallCategoryResponse {

    private Long id;
    private Long mediumCategoryId;
}
