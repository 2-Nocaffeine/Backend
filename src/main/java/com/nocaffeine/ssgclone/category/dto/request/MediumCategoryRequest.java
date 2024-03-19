package com.nocaffeine.ssgclone.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediumCategoryRequest {
    private Long id;
    private String name;
}
