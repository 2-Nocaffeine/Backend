package com.nocaffeine.ssgclone.like.dto;

import com.nocaffeine.ssgclone.like.vo.request.ProductRemoveRequestVo;
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

    public static ProductLikeRemoveDto voToDto(ProductRemoveRequestVo productRemoveRequestVo){
        return ProductLikeRemoveDto.builder()
                .likeFolderId(productRemoveRequestVo.getLikeFolderId())
                .productLikeId(productRemoveRequestVo.getProductLikeId())
                .build();
    }
}
