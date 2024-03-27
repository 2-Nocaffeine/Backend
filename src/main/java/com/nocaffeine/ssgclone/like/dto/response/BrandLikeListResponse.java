package com.nocaffeine.ssgclone.like.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandLikeListResponse {
    private Long brandId;
    private String brandName;
}
