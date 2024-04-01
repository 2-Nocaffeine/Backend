package com.nocaffeine.ssgclone.member.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.member.application.AuthService;
import com.nocaffeine.ssgclone.member.dto.request.AuthCheckRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.AuthEmailRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.SnsMemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponseDto;
import com.nocaffeine.ssgclone.member.vo.request.AuthCheckRequestVo;
import com.nocaffeine.ssgclone.member.vo.request.AuthEmailRequestVo;
import com.nocaffeine.ssgclone.member.vo.request.SnsMemberAddRequestVo;
import com.nocaffeine.ssgclone.member.vo.request.SnsMemberLoginRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "소셜 회원가입", description = "소셜 회원가입")
    @PostMapping("/sns-join")
    public CommonResponse<String> snsCreateMember(@RequestBody SnsMemberAddRequestVo snsMemberAddRequestVo) {
        authService.snsAddMember(SnsMemberAddRequestVo.voToDto(snsMemberAddRequestVo));
        return CommonResponse.success("소셜 회원가입 성공");
    }

    @Operation(summary = "소셜 로그인", description = "소셜 로그인")
    @PostMapping("/sns-login")
    public ResponseEntity<CommonResponse<TokenResponseDto>> snsLogin(@RequestBody SnsMemberLoginRequestVo snsMemberLoginRequestVo) {
        TokenResponseDto tokenResponseDto = authService.snsLogin(SnsMemberLoginRequestDto.voToDto(snsMemberLoginRequestVo));
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, tokenResponseDto.getAccessToken())
                .body(CommonResponse.success("소셜 로그인 성공", tokenResponseDto));
    }


    @Operation(summary = "이메일 인증코드 발송", description = "이메일 인증코드 발송")
    @PostMapping("/email")
    public CommonResponse<String> emailAuth(@RequestBody AuthEmailRequestVo authEmailRequestVo) {
        authService.emailAuth(AuthEmailRequestDto.voToDto(authEmailRequestVo));
        return CommonResponse.success("이메일 인증코드 발송 성공");
    }

    @Operation(summary = "이메일 인증코드 확인", description = "이메일 인증코드 확인")
    @PostMapping("/email/check")
    public CommonResponse<String> emailAuthCodeCheck(@RequestBody AuthCheckRequestVo authCheckRequestVo) {
        authService.emailAuthCodeCheck(AuthCheckRequestDto.voToDto(authCheckRequestVo));
        return CommonResponse.success("이메일 인증코드 확인 성공");
    }


}
