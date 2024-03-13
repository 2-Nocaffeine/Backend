package com.nocaffeine.ssgclone.member.controller;

import com.nocaffeine.ssgclone.ResponseDto;
import com.nocaffeine.ssgclone.member.dto.MemberSaveRequestDto;
import com.nocaffeine.ssgclone.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseDto<Void> createMember(@RequestBody MemberSaveRequestDto memberSaveDto){
        return memberService.createMember(memberSaveDto);
    }

    @GetMapping("/duplication/email")
    public ResponseDto<Void> duplicationEmail(@RequestBody MemberSaveRequestDto memberSaveDto){
        return memberService.duplicationEmail(memberSaveDto);
    }
}
