package com.nocaffeine.ssgclone.like.dto;

import com.nocaffeine.ssgclone.like.domain.LikeFolder;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderAddVo;
import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    public static LikeFolderDto voToDto(LikeFolderAddVo likeFolderAddVo){
        return LikeFolderDto.builder()
                .name(likeFolderAddVo.getName())
                .build();
    }
}
