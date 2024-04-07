package com.nocaffeine.ssgclone.like.vo.response;

import lombok.Getter;

@Getter
public class ProductLikeStatusVo {
    private Long productId;
    private boolean isLike;

    public ProductLikeStatusVo(Long productId, boolean isLike) {
        this.productId = productId;
        this.isLike = isLike;
    }
}
