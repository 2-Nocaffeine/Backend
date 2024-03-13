package com.nocaffeine.ssgclone.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberSaveRequestDto {

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 주소가 올바르지 않습니다. 이메일 주소를 정확하게 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    @NotEmpty(message = "휴대폰번호 번호를 입력해 주세요")
    private String phoneNumber;

}

