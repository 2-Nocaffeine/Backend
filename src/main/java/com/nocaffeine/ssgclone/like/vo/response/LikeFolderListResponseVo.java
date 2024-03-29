package com.nocaffeine.ssgclone.like.vo.response;

import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeFolderListResponseVo {
    private Long folderId;
    private String name;

    public LikeFolderListResponseVo(LikeFolderDto likeFolderDto) {
        this.folderId = likeFolderDto.getLikeFolderId();
        this.name = likeFolderDto.getName();
    }

    public static LikeFolderListResponseVo dtoToVo(LikeFolderDto likeFolderDto){
        return new LikeFolderListResponseVo(likeFolderDto);
    }
}
