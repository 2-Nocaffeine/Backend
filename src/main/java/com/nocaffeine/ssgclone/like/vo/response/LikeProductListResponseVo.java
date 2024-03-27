package com.nocaffeine.ssgclone.like.vo.response;

import com.nocaffeine.ssgclone.like.dto.ProductLikeListDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeProductListResponseVo {
    private Long productLikeId;
    private Long productId;

    public static LikeProductListResponseVo dtoToVo(ProductLikeListDto productLikeListDto){
        return LikeProductListResponseVo.builder()
                .productLikeId(productLikeListDto.getProductLikeId())
                .productId(productLikeListDto.getProductId())
                .build();
    }
}
