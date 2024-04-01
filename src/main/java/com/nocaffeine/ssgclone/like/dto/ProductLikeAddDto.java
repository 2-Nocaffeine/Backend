package com.nocaffeine.ssgclone.like.dto;


import com.nocaffeine.ssgclone.like.vo.request.ProductLikeMoveRequestVo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLikeAddDto {

    private List<Long> likeFolderId;
    private List<Long> productLikeId;


    public static ProductLikeAddDto voToDto(ProductLikeMoveRequestVo productLikeMoveRequestVo){
        return ProductLikeAddDto.builder()
                .likeFolderId(productLikeMoveRequestVo.getLikeFolderId())
                .productLikeId(productLikeMoveRequestVo.getProductLikeId())
                .build();
    }

}
