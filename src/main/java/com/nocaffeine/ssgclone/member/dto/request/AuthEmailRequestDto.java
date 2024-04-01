package com.nocaffeine.ssgclone.member.dto.request;

import com.nocaffeine.ssgclone.member.vo.request.AuthEmailRequestVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthEmailRequestDto {
    private String email;

    public static AuthEmailRequestDto voToDto(AuthEmailRequestVo authEmailRequestVo) {
        return AuthEmailRequestDto.builder()
                .email(authEmailRequestVo.getEmail())
                .build();
    }
}
