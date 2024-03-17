package com.nocaffeine.ssgclone.category.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmallCategoryRequest {

    private String name;
    private Long mediumCategoryId;
}
