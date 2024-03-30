package com.nocaffeine.ssgclone.member.application;

import com.nocaffeine.ssgclone.common.EmailProvider;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.EmailAuth;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.dto.request.*;
import com.nocaffeine.ssgclone.member.dto.response.MemberDetailResponseDto;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponseDto;
import com.nocaffeine.ssgclone.member.infrastructure.EmailAuthRepository;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceImp implements MemberService {


    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticateManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailProvider emailProvider;
    private final EmailAuthRepository emailAuthRepository;


    /**
     * 아이디 중복 확인
     */
    @Override
    public void duplicationEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new BaseException(DUPLICATE_EMAIL);
        }
    }

    /**
     * 회원가입
     */
    @Override
    @Transactional
    public void addMember(MemberSaveRequestDto memberSaveRequestDto) {
        try{
            duplicationEmail(memberSaveRequestDto.getEmail());
        } catch (BaseException e){
            throw new BaseException(DUPLICATE_EMAIL);
        }

        createMember(memberSaveRequestDto);
    }

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
     * 로그인
     */
    @Override
    public TokenResponseDto logIn(MemberLoginRequestDto memberLoginRequestDto) {
        Member member = memberRepository.findByEmail(memberLoginRequestDto.getEmail())
                .orElseThrow(() -> new BaseException(FAILED_TO_LOGIN));
        try{
            authenticateManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            member.getUsername(),
                            memberLoginRequestDto.getPassword()
                    ));
        } catch (Exception e){
            throw new BaseException(FAILED_TO_LOGIN);
        }

        String token = createToken(member);

        return TokenResponseDto.builder()
                                .accessToken(token)
                                .build();
    }


    private String createToken(Member member) {
        return jwtTokenProvider.generateToken(member);
    }


    /**
     * 비밀번호 변경
     */
    @Override
    @Transactional
    public void updatePassword(String memberUuid, MemberPasswordRequestDto memberPasswordRequestDto) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        if(!memberPasswordRequestDto.password.equals(memberPasswordRequestDto.getPasswordCheck())){
            throw new BaseException(FAILED_TO_PASSWORD);
        }

        // 비밀번호 변경
        member.updateHashPassword(memberPasswordRequestDto.getPassword());

    }

    /**
     * 회원 정보 조회
     */
    @Override
    public MemberDetailResponseDto findMember(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        return MemberDetailResponseDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }


    /**
     * 회원 삭제
     */
    @Override
    @Transactional
    public void removeMember(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        memberRepository.delete(member);
    }


    /**
     * 이메일 인증코드 발송
     */
    @Override
    @Transactional
    public void emailAuth(AuthEmailRequestDto authEmailRequestDto) {
        duplicationEmail(authEmailRequestDto.getEmail());

        String authCode = createAuthCode();

        boolean isSuccess = emailProvider.sendAuthMail(authEmailRequestDto.getEmail(), authCode);

        if(!isSuccess){
            throw new BaseException(MASSAGE_SEND_FAILED);
        }

        log.info("이메일 인증코드 : {}", authCode);

        Optional<EmailAuth> email = emailAuthRepository.findByEmail(authEmailRequestDto.getEmail());

        if(email.isPresent()){
            email.get().updateAuthCode(authCode);
        } else {
            EmailAuth emailAuth = EmailAuth.builder()
                    .email(authEmailRequestDto.getEmail())
                    .authCode(authCode)
                    .expireDate(LocalDateTime.now())
                    .build();

            emailAuthRepository.save(emailAuth);
        }
    }

    public static String createAuthCode() {
        String authCode = "";

        for(int count = 0; count < 6; count++){
            authCode += (int)(Math.random() * 10);
        }

        return authCode;
    }

    /**
     * 이메일 인증코드 확인
     */
    @Override
    @Transactional
    public void emailAuthCodeCheck(AuthCheckRequestDto authCheckRequestDto) {
        EmailAuth emailAuth = emailAuthRepository.findByEmail(authCheckRequestDto.getEmail())
                .orElseThrow(() -> new BaseException(NO_EXIST_AUTH));

        if(!emailAuth.getAuthCode().equals(authCheckRequestDto.getAuthCode())){
            throw new BaseException(MASSAGE_VALID_FAILED);
        }

        if (emailAuth.getExpireDate().isBefore(LocalDateTime.now())) {
            // 인증 코드가 만료된 경우
            throw new BaseException(EXPIRED_AUTH_CODE);
        }

        emailAuthRepository.delete(emailAuth);
    }
}

