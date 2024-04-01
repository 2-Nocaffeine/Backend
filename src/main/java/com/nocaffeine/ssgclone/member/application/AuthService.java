package com.nocaffeine.ssgclone.member.application;

import com.nocaffeine.ssgclone.member.dto.request.SnsMemberAddRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.SnsMemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponseDto;

public interface AuthService {
    void snsAddMember(SnsMemberAddRequestDto snsMemberAddRequestDto);
    TokenResponseDto snsLogin(SnsMemberLoginRequestDto snsMemberLoginRequestDto);
}
