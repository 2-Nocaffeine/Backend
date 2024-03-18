package com.nocaffeine.ssgclone.member.application;


import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberLoginRequest;
import com.nocaffeine.ssgclone.member.dto.request.MemberPasswordRequest;
import com.nocaffeine.ssgclone.member.dto.request.MemberSaveRequest;
import com.nocaffeine.ssgclone.member.dto.response.MemberDetailResponse;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    ResponseDto<Void> duplicationEmail(String email);
    ResponseDto<Void> addMember(MemberSaveRequest memberSaveRequest);

    ResponseEntity<ResponseDto<TokenResponse>> logIn(MemberLoginRequest memberLoginRequest);

    ResponseDto<Void> updatePassword(String memberUuid, MemberPasswordRequest memberPasswordRequest);

    ResponseDto<MemberDetailResponse> findMember(String memberUuid);

    ResponseDto<Void> removeMember(String memberUuid);
}
