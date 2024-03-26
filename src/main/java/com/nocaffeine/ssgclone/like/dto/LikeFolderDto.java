package com.nocaffeine.ssgclone.like.dto;

import com.nocaffeine.ssgclone.like.vo.request.LikeFolderAddRequestVo;
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
}
