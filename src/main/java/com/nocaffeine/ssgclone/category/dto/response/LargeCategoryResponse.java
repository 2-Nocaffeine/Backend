package com.nocaffeine.ssgclone.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LargeCategoryResponse {
    private Long largeCategoryId;
    private String largeCategoryName;
    private String imageUrl;
}
