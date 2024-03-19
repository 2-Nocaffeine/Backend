package com.nocaffeine.ssgclone.category.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TinyCategoryResponse {

    private Long id;
    private Long smallCategoryId;
}
