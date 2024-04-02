package com.nocaffeine.ssgclone.review.vo.request;

import lombok.Getter;

@Getter
public class ReviewAddRequestVo {
    private Long productId;
    private String content;
    private int rate;
}
