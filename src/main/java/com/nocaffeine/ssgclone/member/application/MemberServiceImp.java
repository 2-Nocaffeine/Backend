package com.nocaffeine.ssgclone.member.application;

import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.dto.request.MemberLoginRequest;
import com.nocaffeine.ssgclone.member.dto.request.MemberPasswordRequest;
import com.nocaffeine.ssgclone.member.dto.request.MemberSaveRequest;
import com.nocaffeine.ssgclone.member.dto.response.MemberDetailResponse;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponse;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceImp implements MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticateManager;
    private final JwtTokenProvider jwtTokenProvider;

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
    public ResponseDto<Void> addMember(MemberSaveRequest memberSaveRequest) {

        try{
            duplicationEmail(memberSaveRequest.getEmail());
        } catch (BaseException e){
            throw new BaseException("아이디 중복체크를 해주세요.");
        }

        createMember(memberSaveRequest);

        return ResponseDto.success("CreateMember success");
    }

    /**
     * 로그인
     */
    @Override
    public ResponseEntity<ResponseDto<TokenResponse>> logIn(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findByEmail(memberLoginRequest.getEmail())
                .orElseThrow(() -> new BaseException("아이디 또는 비밀번호가 일치하지 않습니다. 다시 확인하신 후 입력해주세요."));
        try{
            authenticateManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            member.getUsername(),
                            memberLoginRequest.getPassword()
                    ));
        } catch (Exception e){
            throw new BaseException("아이디 또는 비밀번호가 일치하지 않습니다. 다시 확인하신 후 입력해주세요.");
        }

        String token = createToken(member);
        log.info("token: {}", token);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token);

        return ResponseEntity.ok().headers(httpHeaders)
                .body(ResponseDto.success("login success",
                        TokenResponse.builder()
                                .accessToken(token)
                                .build()));
    }

    /**
     * 비밀번호 변경
     */
    @Override
    @Transactional
    public ResponseDto<Void> updatePassword(String memberUuid, MemberPasswordRequest memberPasswordRequest) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException("회원을 찾을 수 없습니다."));

        if(!memberPasswordRequest.password.equals(memberPasswordRequest.getPasswordCheck())){
            throw new BaseException("비밀번호를 다시 한번 확인 해 주세요.");
        }

        // 비밀번호 변경
        member.updateHashPassword(memberPasswordRequest.getPassword());

        return ResponseDto.success("비밀번호를 변경했습니다.");
    }

    /**
     * 회원 정보 조회
     */
    @Override
    public ResponseDto<MemberDetailResponse> findMember(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException("회원을 찾을 수 없습니다."));

        return ResponseDto.success("FindMember Success",
                MemberDetailResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .build());
    }


    /**
     * 회원 삭제
     */
    @Override
    @Transactional
    public ResponseDto<Void> removeMember(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException("회원을 찾을 수 없습니다."));

        memberRepository.delete(member);

        return ResponseDto.success("RemoveMember Success.");
    }



    private void createMember(MemberSaveRequest memberSaveRequest) {
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .email(memberSaveRequest.getEmail())
                .password(memberSaveRequest.getPassword())
                .uuid(uuid)
                .name(memberSaveRequest.getName())
                .phoneNumber(memberSaveRequest.getPhoneNumber())
                .build();

        // 비밀번호 암호화
        member.hashPassword(memberSaveRequest.getPassword());

        memberRepository.save(member);
    }


    private String createToken(Member member) {
        return jwtTokenProvider.generateToken(member);
    }
}

