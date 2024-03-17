package com.nocaffeine.ssgclone.category.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediumCategoryRequest {

    private String name;
    private Long largeCategoryId;
}
