package com.nocaffeine.ssgclone.review.dto.request;

import com.nocaffeine.ssgclone.review.vo.request.ReviewAddRequestVo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewAddRequestDto {

    private List<String> imageUrl;

    private Long productId;
    private Long orderId;
    private String content;
    private int rate;

    public static ReviewAddRequestDto voToDto(ReviewAddRequestVo reviewAddRequestVo) {
        return ReviewAddRequestDto.builder()
                .imageUrl(reviewAddRequestVo.getImageUrl())
                .productId(reviewAddRequestVo.getProductId())
                .orderId(reviewAddRequestVo.getOrderId())
                .content(reviewAddRequestVo.getContent())
                .rate(reviewAddRequestVo.getRate())
                .build();

    }
}
