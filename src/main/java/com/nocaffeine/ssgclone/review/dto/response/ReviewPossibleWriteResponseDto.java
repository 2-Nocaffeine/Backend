package com.nocaffeine.ssgclone.review.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewPossibleWriteResponseDto {
    private Long productId;
    private String productName;

}
