package com.nocaffeine.ssgclone.review.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRemoveRequestDto {
    private Long reviewId;
}
