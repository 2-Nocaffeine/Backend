package com.nocaffeine.ssgclone.review.vo.response;

import lombok.Getter;

@Getter
public class ReviewDetailResponseVo {
    private Long reviewId;
    private String memberName;
    private String content;
    private int rate;
    private String createdAt;

    public ReviewDetailResponseVo(Long reviewId, String memberName, String content, int rate, String createdAt) {
        this.reviewId = reviewId;
        this.memberName = memberName;
        this.content = content;
        this.rate = rate;
        this.createdAt = createdAt;
    }
}
