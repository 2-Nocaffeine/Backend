package com.nocaffeine.ssgclone.like.vo.response;

import lombok.Getter;

@Getter
public class BrandLikeStatusVo {
    private Long brandId;
    private boolean isLike;

    public BrandLikeStatusVo(Long brandId, boolean isLike) {
        this.brandId = brandId;
        this.isLike = isLike;
    }
}
