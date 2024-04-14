package com.nocaffeine.ssgclone.member.application;


import com.nocaffeine.ssgclone.member.dto.request.*;
import com.nocaffeine.ssgclone.member.dto.response.MemberDetailResponseDto;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponseDto;

public interface MemberService {


    void updatePassword(String memberUuid, MemberPasswordRequestDto memberPasswordRequestDto);

    MemberDetailResponseDto findMember(String memberUuid);

    void removeMember(String memberUuid);

}
