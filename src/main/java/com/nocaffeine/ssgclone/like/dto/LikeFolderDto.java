package com.nocaffeine.ssgclone.like.dto;

import com.nocaffeine.ssgclone.like.vo.request.LikeFolderAddRequestVo;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderModifyRequestVo;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderRemoveVo;
import com.nocaffeine.ssgclone.member.domain.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeFolderDto {

    private Long id;
    private Member member;
    private String name;

    public static LikeFolderDto voToDto(LikeFolderAddRequestVo likeFolderAddRequestVo){
        return LikeFolderDto.builder()
                .name(likeFolderAddRequestVo.getName())
                .build();
    }

    public static LikeFolderDto voToDto(LikeFolderModifyRequestVo likeFolderModifyRequestVo){
        return LikeFolderDto.builder()
                .id(likeFolderModifyRequestVo.getFolderId())
                .name(likeFolderModifyRequestVo.getName())
                .build();
    }

    public static LikeFolderDto voToDto(LikeFolderRemoveVo likeFolderRemoveVo){
        return LikeFolderDto.builder()
                .id(likeFolderRemoveVo.getFolderId())
                .build();
    }
}