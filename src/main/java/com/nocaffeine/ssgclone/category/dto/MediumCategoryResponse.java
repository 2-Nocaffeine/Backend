package com.nocaffeine.ssgclone.category.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediumCategoryResponse {

    private Long id;
    private Long largeCategoryId;
}
