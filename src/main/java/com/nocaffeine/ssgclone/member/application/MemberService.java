package com.nocaffeine.ssgclone.member.application;


import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberPasswordRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberSaveRequestDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    public ResponseDto<Void> duplicationEmail(String email);
    public ResponseDto<Void> signUp(MemberSaveRequestDto memberSaveRequestDto);

    public ResponseEntity<ResponseDto<Object>> logIn(MemberLoginRequestDto memberLoginRequestDto);

    public ResponseDto<Void> changePassword(String memberUuid, MemberPasswordRequestDto memberPasswordRequestDto);

}
