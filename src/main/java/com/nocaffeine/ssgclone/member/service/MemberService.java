package com.nocaffeine.ssgclone.member.service;


import com.nocaffeine.ssgclone.ResponseDto;
import com.nocaffeine.ssgclone.member.dto.MemberSaveRequestDto;

public interface MemberService {

    public ResponseDto<Void> duplicationEmail(MemberSaveRequestDto memberSaveDto);
    public ResponseDto<Void> createMember(MemberSaveRequestDto memberSaveDto);

}
