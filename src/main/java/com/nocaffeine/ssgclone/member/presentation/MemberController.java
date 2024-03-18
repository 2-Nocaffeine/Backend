package com.nocaffeine.ssgclone.member.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.member.dto.request.MemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberPasswordRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberSaveRequestDto;
import com.nocaffeine.ssgclone.member.application.MemberService;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "이메일 중복 검증", description = "이메일 중복 검증", tags = {"Duplication Email"})
    @GetMapping("/duplication")
    public CommonResponse<Void> duplicationEmail(@RequestParam String email) {
        return memberService.duplicationEmail(email);
    }

    @Operation(summary = "회원가입", description = "회원가입", tags = {"Sign Up"})
    @PostMapping("/member")
    public CommonResponse<Void> createMember(@Valid @RequestBody MemberSaveRequestDto memberSaveDto) {
        return memberService.signUp(memberSaveDto);
    }

    @Operation(summary = "로그인", description = "로그인", tags = {"Log In"})
    @PostMapping("/member/login")
    public ResponseEntity<CommonResponse<Object>> logIn(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        return memberService.logIn(memberLoginRequestDto);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경", tags = {"Change Password"})
    @PatchMapping("/member/password")
    public CommonResponse<Void> changePassword(@RequestBody MemberPasswordRequestDto memberPasswordRequestDto) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return memberService.changePassword(memberUuid, memberPasswordRequestDto);
    }
}
