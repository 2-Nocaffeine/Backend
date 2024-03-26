package com.nocaffeine.ssgclone.like.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryLikeListResponse {
    private Long CategoryLikeId;
    private Long mediumCategoryId;
    private Long smallCategoryId;
    private Long tinyCategoryId;
}
