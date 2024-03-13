package com.nocaffeine.ssgclone.member.service;

import com.nocaffeine.ssgclone.ResponseDto;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.dto.MemberSaveRequestDto;
import com.nocaffeine.ssgclone.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImp implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ResponseDto<Void> duplicationEmail(MemberSaveRequestDto memberSaveRequestDto) {
        if (memberRepository.findByEmail(memberSaveRequestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 아이디 입니다. 다른 아이디를 입력해주세요.");
        }
        return ResponseDto.success("duplicationEmail success");
    }

    @Override
    @Transactional
    public ResponseDto<Void> createMember(MemberSaveRequestDto memberSaveRequestDto) {
        // 아이디 중복 확인
        ResponseDto<Void> duplicationResponse = duplicationEmail(memberSaveRequestDto);
        if (!duplicationResponse.isSuccess()) {
            return duplicationResponse;
        }

        Member member = Member.builder()
                .email(memberSaveRequestDto.getEmail())
                .password(memberSaveRequestDto.getPassword())
                .name(memberSaveRequestDto.getName())
                .phoneNumber(memberSaveRequestDto.getPhoneNumber())
                .address(memberSaveRequestDto.getAddress())
                .build();

        memberRepository.save(member);

        return ResponseDto.success("CreateMember success");
    }
}
