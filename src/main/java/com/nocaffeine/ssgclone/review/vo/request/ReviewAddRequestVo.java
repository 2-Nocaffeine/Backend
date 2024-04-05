package com.nocaffeine.ssgclone.review.vo.request;

import lombok.Getter;

import java.util.List;

@Getter
public class ReviewAddRequestVo {
    private List<String> imageUrl;

    private Long productId;
    private Long orderId;
    private String content;
    private int rate;
}
