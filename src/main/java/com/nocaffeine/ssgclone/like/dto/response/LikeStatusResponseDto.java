package com.nocaffeine.ssgclone.like.dto.response;

import com.nocaffeine.ssgclone.like.vo.response.BrandLikeStatusVo;
import com.nocaffeine.ssgclone.like.vo.response.ProductLikeStatusVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeStatusResponseDto {
    private Long productId;
    private Long categoryId;
    private Long brandId;
    private boolean isLike;

    public static ProductLikeStatusVo dtoToProductVo(LikeStatusResponseDto productLike) {
        return new ProductLikeStatusVo(productLike.getProductId(), productLike.isLike());
    }

    public static BrandLikeStatusVo dtoToBrandVo(LikeStatusResponseDto brandLike) {
        return new BrandLikeStatusVo(brandLike.getBrandId(), brandLike.isLike());
    }
}
