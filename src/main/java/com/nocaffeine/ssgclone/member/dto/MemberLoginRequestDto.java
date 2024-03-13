package com.nocaffeine.ssgclone.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberLoginRequestDto {
    private String email;
    private String password;
}
