package com.nocaffeine.ssgclone.review.dto.request;

import com.nocaffeine.ssgclone.review.vo.request.ReviewModifyRequestVo;
import com.nocaffeine.ssgclone.review.vo.request.ReviewRemoveRequestVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRemoveRequestDto {
    private Long reviewId;

    public static ReviewRemoveRequestDto voToDto(ReviewRemoveRequestVo reviewRemoveRequestVo) {
        return ReviewRemoveRequestDto.builder()
                .reviewId(reviewRemoveRequestVo.getReviewId())
                .build();
    }
}
