package com.nocaffeine.ssgclone.like.vo.response;

import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeFolderListResponseVo {
    private Long folderId;
    private String name;

    public static LikeFolderListResponseVo dtoToVo(LikeFolderDto likeFolderDto){
        return LikeFolderListResponseVo.builder()
                .folderId(likeFolderDto.getLikeFolderId())
                .name(likeFolderDto.getName())
                .build();
    }
}
