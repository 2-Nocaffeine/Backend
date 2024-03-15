package com.nocaffeine.ssgclone.member.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPasswordRequestDto {
    public String password;
    public String PasswordCheck;
}
