package com.nocaffeine.ssgclone.review.vo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class ReviewDetailResponseVo {
    private Long reviewId;
    private String memberName;
    private String content;
    private int rate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;


    public ReviewDetailResponseVo(Long reviewId, String memberName, String content, int rate, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.memberName = memberName;
        this.content = content;
        this.rate = rate;
        this.createdAt = createdAt;
    }
}
