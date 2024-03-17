package com.nocaffeine.ssgclone.category.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TinyCategoryRequest {

    private String name;
    private Long smallCategoryId;
}
