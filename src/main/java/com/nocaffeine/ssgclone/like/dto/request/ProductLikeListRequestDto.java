package com.nocaffeine.ssgclone.like.dto.request;

import com.nocaffeine.ssgclone.like.vo.request.ProductLikeListRequestVo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLikeListRequestDto {
    private List<Long> productId;

    public static ProductLikeListRequestDto voToDto(ProductLikeListRequestVo productLikeListRequestVo) {
        return ProductLikeListRequestDto.builder()
                .productId(productLikeListRequestVo.getProductId())
                .build();


    }
}
