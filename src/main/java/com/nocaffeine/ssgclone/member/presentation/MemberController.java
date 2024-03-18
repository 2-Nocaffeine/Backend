package com.nocaffeine.ssgclone.member.presentation;

import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberLoginRequest;
import com.nocaffeine.ssgclone.member.dto.request.MemberPasswordRequest;
import com.nocaffeine.ssgclone.member.dto.request.MemberSaveRequest;
import com.nocaffeine.ssgclone.member.application.MemberService;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.member.dto.response.MemberDetailResponse;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
    public ResponseDto<String> duplicationEmail(@RequestParam String email) {
        memberService.duplicationEmail(email);
        return ResponseDto.success("이메일 중복 검증 성공");
    }

    @Operation(summary = "회원가입", description = "회원가입", tags = {"Sign Up"})
    @PostMapping("/member")
    public ResponseDto<String> memberCreate(@Valid @RequestBody MemberSaveRequest memberSaveDto) {
        memberService.addMember(memberSaveDto);
        return ResponseDto.success("회원가입 성공");
    }

    @Operation(summary = "로그인", description = "로그인", tags = {"Log In"})
    @PostMapping("/member/login")
    public ResponseEntity<ResponseDto<TokenResponse>> logIn(@RequestBody MemberLoginRequest memberLoginRequest) {
        // todo : 이미 로그인 되어있을 경우?
        TokenResponse tokenResponse = memberService.logIn(memberLoginRequest);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, tokenResponse.getAccessToken())
                .body(ResponseDto.success("로그인 성공", tokenResponse));
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경", tags = {"Change Password"})
    @PatchMapping("/member/password")
    public ResponseDto<String> changePassword(@RequestBody MemberPasswordRequest memberPasswordRequest) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        memberService.updatePassword(memberUuid, memberPasswordRequest);
        return ResponseDto.success("비밀번호 변경 성공");
    }

    @Operation(summary = "회원 상세 정보", description = "회원 상세 정보", tags = {"Member Detail"})
    @GetMapping("/member")
    public ResponseDto<MemberDetailResponse> memberDetail() {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return ResponseDto.success("회원 상세 조회 성공",memberService.findMember(memberUuid));
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴", tags = {"Remove Member"})
    @DeleteMapping("/member")
    public ResponseDto<String> memberRemove() {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        memberService.removeMember(memberUuid);
        return ResponseDto.success("회원 탈퇴 성공");
    }

}
