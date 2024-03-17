package com.nocaffeine.ssgclone.product.dto.request;

import com.nocaffeine.ssgclone.product.domain.Product;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalResponse {

    // Total 엔티티의 필드들을 가져온다.
    private Long id;
    private Product product;
    private int sales;
    private Double rateAverage;
    private int reviewCount;
}
