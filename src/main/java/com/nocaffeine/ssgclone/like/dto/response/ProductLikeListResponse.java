package com.nocaffeine.ssgclone.like.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLikeListResponse {
    private Long productLikeId;
    private Long productId;

}
