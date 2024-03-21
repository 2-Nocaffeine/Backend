package com.nocaffeine.ssgclone.category.dto.response;

import lombok.*;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class ProductIdResponse {
    private static final AtomicLong counter = new AtomicLong();

    private Long id;
    private Long product_id;

    public ProductIdResponse(Long product_id) {
        this.id = counter.incrementAndGet();
        this.product_id = product_id;
    }
}
