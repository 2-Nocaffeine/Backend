package com.nocaffeine.ssgclone.member.vo.request;

import com.nocaffeine.ssgclone.member.dto.request.MemberSaveRequestDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberSaveRequestVo {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

}
