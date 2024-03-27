package com.nocaffeine.ssgclone.like.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeProductListDto {
    private Long productLikeId;
    private Long productId;

}
