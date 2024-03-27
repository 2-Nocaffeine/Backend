package com.nocaffeine.ssgclone.member.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDetailResponseVo {
    private String email;
    private String name;
    private String phoneNumber;
    private String address;

    public MemberDetailResponseVo(String email, String name, String phoneNumber, String address) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


}
