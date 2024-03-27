package com.nocaffeine.ssgclone.like.dto;


import com.nocaffeine.ssgclone.like.vo.request.LikeFolderRemoveRequestVo;
import com.nocaffeine.ssgclone.like.vo.request.ProductLikeMoveRequestVo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeAddFolderDto {

    private List<Long> likeFolderId;
    private List<Long> productLikeId;


    public static LikeAddFolderDto voToDto(ProductLikeMoveRequestVo productLikeMoveRequestVo){
        return LikeAddFolderDto.builder()
                .likeFolderId(productLikeMoveRequestVo.getLikeFolderId())
                .productLikeId(productLikeMoveRequestVo.getProductLikeId())
                .build();
    }

}
