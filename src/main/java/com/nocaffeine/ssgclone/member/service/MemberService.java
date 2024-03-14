package com.nocaffeine.ssgclone.member.service;

import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    Member member = memberRepository.findByUUID(UUID.fromString(uuid)).orElse(null);
}
