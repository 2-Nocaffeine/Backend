package com.nocaffeine.ssgclone.member.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginRequestDto {
    private String email;
    private String password;
}
