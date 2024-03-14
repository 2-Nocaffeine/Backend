package com.nocaffeine.ssgclone.member.service;


import com.nocaffeine.ssgclone.ResponseDto;
import com.nocaffeine.ssgclone.member.dto.MemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.MemberPasswordRequestDto;
import com.nocaffeine.ssgclone.member.dto.MemberSaveRequestDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    public ResponseDto<Void> duplicationEmail(String email);
    public ResponseDto<Void> signUp(MemberSaveRequestDto memberSaveRequestDto);

    public ResponseEntity<ResponseDto<Object>> logIn(MemberLoginRequestDto memberLoginRequestDto);

    public ResponseDto<Void> changePassword(String token, MemberPasswordRequestDto memberPasswordRequestDto);

}
