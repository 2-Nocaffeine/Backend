package com.nocaffeine.ssgclone.like.vo.response;

import com.nocaffeine.ssgclone.like.dto.ProductLikeListDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeProductListResponseVo {
    private Long productLikeId;
    private Long productId;

    public LikeProductListResponseVo(ProductLikeListDto productLikeListDto) {
        this.productLikeId = productLikeListDto.getProductLikeId();
        this.productId = productLikeListDto.getProductId();
    }

    public static LikeProductListResponseVo dtoToVo(ProductLikeListDto productLikeListDto){
        return new LikeProductListResponseVo(productLikeListDto);
    }

}
