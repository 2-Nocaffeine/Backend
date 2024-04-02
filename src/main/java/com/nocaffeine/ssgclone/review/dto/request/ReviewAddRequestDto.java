package com.nocaffeine.ssgclone.review.dto.request;

import com.nocaffeine.ssgclone.review.vo.request.ReviewAddRequestVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAddRequestDto {
    private Long productId;
    private String content;
    private int rate;


    public static ReviewAddRequestDto voToDto(ReviewAddRequestVo reviewAddRequestVo) {
        return ReviewAddRequestDto.builder()
                .productId(reviewAddRequestVo.getProductId())
                .content(reviewAddRequestVo.getContent())
                .rate(reviewAddRequestVo.getRate())
                .build();

    }
}
