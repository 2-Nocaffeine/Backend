package com.nocaffeine.ssgclone.review.dto.response;


import com.nocaffeine.ssgclone.review.vo.response.ReviewListResponseVo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewListResponseDto {

    private Long reviewId;
    private String memberName;
    private String content;
    private int rate;
    private String createdAt;


    public static ReviewListResponseVo dtoToVo(ReviewListResponseDto reviewListResponseDto) {
        return new ReviewListResponseVo(reviewListResponseDto.getReviewId(), reviewListResponseDto.getMemberName(),
                reviewListResponseDto.getContent(), reviewListResponseDto.getRate(), reviewListResponseDto.getCreatedAt());
    }
}
