package com.nocaffeine.ssgclone.member.service;

import com.nocaffeine.ssgclone.ResponseDto;
import com.nocaffeine.ssgclone.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.dto.MemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.MemberPasswordRequestDto;
import com.nocaffeine.ssgclone.member.dto.MemberSaveRequestDto;
import com.nocaffeine.ssgclone.member.repository.MemberRepository;
import com.nocaffeine.ssgclone.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceImp implements MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticateManager;

    /**
     * 아이디 중복 확인
     */
    @Override
    public ResponseDto<Void> duplicationEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new BaseException("이미 사용중인 아이디 입니다. 다른 아이디를 입력해주세요.");
        }
        return ResponseDto.success("duplicationEmail success");
    }

    /**
     * 회원가입
     */
    @Override
    @Transactional
    public ResponseDto<Void> signUp(MemberSaveRequestDto memberSaveRequestDto) {
        // 아이디 중복 확인
        duplicationEmail(memberSaveRequestDto.getEmail());
        createMember(memberSaveRequestDto);

        return ResponseDto.success("CreateMember success");
    }

    /**
     * 로그인
     */
    @Override
    public ResponseEntity<ResponseDto<Object>> logIn(MemberLoginRequestDto memberLoginRequestDto) {
        Member member = memberRepository.findByEmail(memberLoginRequestDto.getEmail())
                .orElseThrow(() -> new BaseException("아이디 또는 비밀번호가 일치하지 않습니다. 다시 확인하신 후 입력해주세요."));


        try{
            authenticateManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            member.getUsername(),
                            memberLoginRequestDto.getPassword()
                    ));
        } catch (Exception e){
            throw new BaseException("아이디 또는 비밀번호가 일치하지 않습니다. 다시 확인하신 후 입력해주세요.");
        }

        String token = createToken(member);
        log.info("token: {}", token);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        return ResponseEntity.ok().headers(httpHeaders).body(ResponseDto.success("login success", token));
    }

    /**
     * 비밀번호 변경
     */
    @Override
    public ResponseDto<Void> changePassword(String token, MemberPasswordRequestDto memberPasswordRequestDto) {
        String uuid = jwtTokenProvider.getUuid(token);

        // 회원 정보 조회
        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new BaseException("회원을 찾을 수 없습니다."));

        // 비밀번호 변경
        member.changePassword(memberPasswordRequestDto.getPassword());

        return ResponseDto.success("비밀번호가 성공적으로 변경되었습니다.");
    }


    /**
     * 회원 생성 메서드
     */
    private void createMember(MemberSaveRequestDto memberSaveRequestDto) {
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .email(memberSaveRequestDto.getEmail())
                .password(memberSaveRequestDto.getPassword())
                .uuid(uuid)
                .name(memberSaveRequestDto.getName())
                .phoneNumber(memberSaveRequestDto.getPhoneNumber())
                .build();

        // 비밀번호 암호화
        member.hashPassword(memberSaveRequestDto.getPassword());

        memberRepository.save(member);
    }

    /**
     * 토큰 생성 메서드
     */
    private String createToken(Member member) {
        return jwtTokenProvider.generateToken(member);
    }
}

