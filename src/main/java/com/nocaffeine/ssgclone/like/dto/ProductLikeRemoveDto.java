package com.nocaffeine.ssgclone.like.dto;

import com.nocaffeine.ssgclone.like.vo.request.ProductLikeRemoveRequestVo;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLikeRemoveDto {
    private Long likeFolderId;
    private List<Long> productLikeId;

    public static ProductLikeRemoveDto voToDto(ProductLikeRemoveRequestVo productLikeRemoveRequestVo){
        return ProductLikeRemoveDto.builder()
                .likeFolderId(productLikeRemoveRequestVo.getLikeFolderId())
                .productLikeId(productLikeRemoveRequestVo.getProductLikeId())
                .build();
    }
}
