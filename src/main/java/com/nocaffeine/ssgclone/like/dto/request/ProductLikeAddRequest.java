package com.nocaffeine.ssgclone.like.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLikeAddRequest {
    private Long productId;
}
