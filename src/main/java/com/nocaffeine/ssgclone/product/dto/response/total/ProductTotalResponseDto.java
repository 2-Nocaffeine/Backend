package com.nocaffeine.ssgclone.product.dto.response.total;

import com.nocaffeine.ssgclone.product.vo.response.total.ProductTotalResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTotalResponseDto {
    private Long totalId;
    private Long productId;
    private int sales;
    private BigDecimal rateAverage;
    private int reviewCount;

    public static ProductTotalResponseVo dtoToVo(ProductTotalResponseDto productTotal) {
        return new ProductTotalResponseVo(
                productTotal.getTotalId(),
                productTotal.getProductId(),
                productTotal.getSales(),
                productTotal.getRateAverage().setScale(1, RoundingMode.DOWN).doubleValue(), // 소수점 첫째자리까지 표시
                productTotal.getReviewCount());
    }
}
