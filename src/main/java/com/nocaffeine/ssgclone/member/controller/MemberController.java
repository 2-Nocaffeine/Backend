package com.nocaffeine.ssgclone.member.controller;

import com.nocaffeine.ssgclone.ResponseDto;
import com.nocaffeine.ssgclone.member.dto.MemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.MemberSaveRequestDto;
import com.nocaffeine.ssgclone.member.service.MemberService;
import com.nocaffeine.ssgclone.security.JwtTokenProvider;
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

    @Operation(summary = "이메일 중복 검증", description = "이메일 중복 검증", tags = { "Duplication Email" })
    @GetMapping("/duplication/email")
    public ResponseDto<Void> duplicationEmail(@Valid @RequestBody MemberSaveRequestDto memberSaveDto){
        return memberService.duplicationEmail(memberSaveDto);
    }

    @Operation(summary = "회원가입", description = "회원가입", tags = { "Sign Up" })
    @PostMapping("/member")
    public ResponseDto<Void> createMember(@Valid @RequestBody MemberSaveRequestDto memberSaveDto){
        return memberService.signUp(memberSaveDto);
    }

    @Operation(summary = "로그인", description = "로그인", tags = { "Log In" })
    @PostMapping("/member/login")
    public ResponseEntity<ResponseDto<Object>> logIn(@RequestBody MemberLoginRequestDto memberLoginRequestDto){
        log.info("login");
        return memberService.logIn(memberLoginRequestDto);
    }
}
