package com.nocaffeine.ssgclone.like.dto;

import com.nocaffeine.ssgclone.like.vo.request.LikeFolderAddRequestVo;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderModifyRequestVo;
import com.nocaffeine.ssgclone.member.domain.Member;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeFolderDto {

    private Long likeFolderId;
    private Member member;
    private String name;


    public static LikeFolderDto voToDto(LikeFolderAddRequestVo likeFolderAddRequestVo){
        return LikeFolderDto.builder()
                .name(likeFolderAddRequestVo.getName())
                .build();
    }

    public static LikeFolderDto voToDto(LikeFolderModifyRequestVo likeFolderModifyRequestVo){
        return LikeFolderDto.builder()
                .name(likeFolderModifyRequestVo.getName())
                .build();
    }
}
