package com.nocaffeine.ssgclone.member.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.member.application.AuthService;
import com.nocaffeine.ssgclone.member.dto.request.SnsMemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponseDto;
import com.nocaffeine.ssgclone.member.vo.request.SnsMemberAddRequestVo;
import com.nocaffeine.ssgclone.member.vo.request.SnsMemberLoginRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sns-join")
    public CommonResponse<String> snsCreateMember(@RequestBody SnsMemberAddRequestVo snsMemberAddRequestVo) {
        authService.snsAddMember(SnsMemberAddRequestVo.voToDto(snsMemberAddRequestVo));
        return CommonResponse.success("소셜 회원가입 성공");
    }

    @PostMapping("/sns-login")
    public ResponseEntity<CommonResponse<TokenResponseDto>> snsLogin(@RequestBody SnsMemberLoginRequestVo snsMemberLoginRequestVo) {
        TokenResponseDto tokenResponseDto = authService.snsLogin(SnsMemberLoginRequestDto.voToDto(snsMemberLoginRequestVo));
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, tokenResponseDto.getAccessToken())
                .body(CommonResponse.success("소셜 로그인 성공", tokenResponseDto));
    }

}
