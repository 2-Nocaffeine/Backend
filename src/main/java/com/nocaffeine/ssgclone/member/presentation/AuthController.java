package com.nocaffeine.ssgclone.member.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.member.application.AuthService;
import com.nocaffeine.ssgclone.member.dto.request.*;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponseDto;
import com.nocaffeine.ssgclone.member.vo.request.*;
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

    @Operation(summary = "이메일 중복 검증", description = "이메일 중복 검증")
    @GetMapping("/duplication")
    public CommonResponse<String> duplicationEmail(@RequestParam("email") String email) {
        authService.duplicationEmail(email);
        return CommonResponse.success("이메일 중복 검증 성공");
    }

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


    @Operation(summary = "일반 회원가입", description = "일반 회원가입")
    @PostMapping("/join")
    public CommonResponse<String> memberCreate(@RequestBody MemberSaveRequestVo memberSaveRequestVo) {
        authService.addMember(MemberSaveRequestDto.voToDto(memberSaveRequestVo));
        return CommonResponse.success("회원가입 성공");
    }

    @Operation(summary = "일반 로그인", description = "일반 로그인")
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<TokenResponseDto>> logIn(@RequestBody MemberLoginRequestVo memberLoginRequestVo) {
        TokenResponseDto tokenResponseDto = authService.logIn(MemberLoginRequestDto.voToDto(memberLoginRequestVo));
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, tokenResponseDto.getAccessToken())
                .body(CommonResponse.success("로그인 성공"));
    }


    @Operation(summary = "이메일 인증코드 발송", description = "이메일 인증코드 발송")
    @PostMapping("/email")
    public CommonResponse<String> emailAuth(@RequestBody AuthEmailRequestVo authEmailRequestVo) {
        authService.emailAuth(AuthEmailRequestDto.voToDto(authEmailRequestVo));
        return CommonResponse.success("이메일 인증코드 발송 성공");
    }

    @Operation(summary = "이메일 인증코드 확인", description = "이메일 인증코드 확인")
    @GetMapping("/email")
    public CommonResponse<String> emailAuthCodeCheck(    @RequestParam("email") String email,
                                                         @RequestParam("code") String code )
    {
        authService.emailAuthCodeCheck(email, code);
        return CommonResponse.success("이메일 인증코드 확인 성공");
    }


}
