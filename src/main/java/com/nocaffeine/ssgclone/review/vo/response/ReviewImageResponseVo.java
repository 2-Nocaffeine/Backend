package com.nocaffeine.ssgclone.review.vo.response;

import lombok.Getter;

@Getter
public class ReviewImageResponseVo {
    private String imageUrl;

    public ReviewImageResponseVo(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
