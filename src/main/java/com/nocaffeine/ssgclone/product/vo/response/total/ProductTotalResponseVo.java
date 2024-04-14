package com.nocaffeine.ssgclone.product.vo.response.total;

import lombok.*;

@Getter
public class ProductTotalResponseVo {
    private Long totalId;
    private Long productId;
    private int sales;

    private Double rateAverage;

    private int reviewCount;

    public ProductTotalResponseVo(Long totalId, Long productId, int sales, Double rateAverage, int reviewCount) {
        this.totalId = totalId;
        this.productId = productId;
        this.sales = sales;
        this.rateAverage = rateAverage;
        this.reviewCount = reviewCount;
    }
}
