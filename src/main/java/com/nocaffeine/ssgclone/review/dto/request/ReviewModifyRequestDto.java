package com.nocaffeine.ssgclone.review.dto.request;

import com.nocaffeine.ssgclone.review.vo.request.ReviewModifyRequestVo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewModifyRequestDto {

    private Long reviewId;
    private String content;
    private int rate;

    private List<String> imageUrl;

    public static ReviewModifyRequestDto voToDto(ReviewModifyRequestVo reviewModifyRequestVo) {
        return ReviewModifyRequestDto.builder()
                .reviewId(reviewModifyRequestVo.getReviewId())
                .content(reviewModifyRequestVo.getContent())
                .rate(reviewModifyRequestVo.getRate())
                .imageUrl(reviewModifyRequestVo.getImageUrl())
                .build();
    }
}
