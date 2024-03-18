package com.nocaffeine.ssgclone.member.application;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.member.dto.request.MemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberPasswordRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberSaveRequestDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    public CommonResponse<Void> duplicationEmail(String email);
    public CommonResponse<Void> signUp(MemberSaveRequestDto memberSaveRequestDto);

    public ResponseEntity<CommonResponse<Object>> logIn(MemberLoginRequestDto memberLoginRequestDto);

    public CommonResponse<Void> changePassword(String memberUuid, MemberPasswordRequestDto memberPasswordRequestDto);

    ResponseDto<MemberDetailResponse> findMember(String memberUuid);

    ResponseDto<Void> removeMember(String memberUuid);
}
