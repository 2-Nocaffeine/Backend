package com.nocaffeine.ssgclone.member.application;


import com.nocaffeine.ssgclone.member.dto.request.MemberLoginRequest;
import com.nocaffeine.ssgclone.member.dto.request.MemberPasswordRequest;
import com.nocaffeine.ssgclone.member.dto.request.MemberSaveRequest;
import com.nocaffeine.ssgclone.member.dto.response.MemberDetailResponse;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponse;

public interface MemberService {

    void duplicationEmail(String email);
    void addMember(MemberSaveRequest memberSaveRequest);

    TokenResponse logIn(MemberLoginRequest memberLoginRequest);

    void updatePassword(String memberUuid, MemberPasswordRequest memberPasswordRequest);

    MemberDetailResponse findMember(String memberUuid);

    void removeMember(String memberUuid);

}
