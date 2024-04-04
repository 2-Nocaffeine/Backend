package com.nocaffeine.ssgclone.review.dto.response;


import com.nocaffeine.ssgclone.review.vo.response.ReviewImageResponseVo;
import com.nocaffeine.ssgclone.review.vo.response.ReviewListResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewImageResponseDto {
    private String imageUrl;

    public static ReviewImageResponseVo dtoToVo(ReviewImageResponseDto reviewImageResponseDto) {
        return new ReviewImageResponseVo(reviewImageResponseDto.getImageUrl());
    }
}
