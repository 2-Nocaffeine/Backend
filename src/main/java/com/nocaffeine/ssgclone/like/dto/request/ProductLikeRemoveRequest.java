package com.nocaffeine.ssgclone.like.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLikeRemoveRequest {
    private Long productId;
}
