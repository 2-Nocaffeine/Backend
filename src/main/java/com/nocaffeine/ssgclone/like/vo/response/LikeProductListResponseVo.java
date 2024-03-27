package com.nocaffeine.ssgclone.like.vo.response;

import com.nocaffeine.ssgclone.like.dto.LikeProductListDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeProductListResponseVo {
    private Long productLikeId;
    private Long productId;

    public static LikeProductListResponseVo dtoToVo(LikeProductListDto likeProductListDto){
        return LikeProductListResponseVo.builder()
                .productLikeId(likeProductListDto.getProductLikeId())
                .productId(likeProductListDto.getProductId())
                .build();
    }
}
