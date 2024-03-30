package com.nocaffeine.ssgclone.member.application;

import com.nocaffeine.ssgclone.common.EmailProvider;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.dto.request.MemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberPasswordRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberSaveRequestDto;
import com.nocaffeine.ssgclone.member.dto.response.MemberDetailResponseDto;
import com.nocaffeine.ssgclone.member.dto.response.TokenResponseDto;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.member.vo.request.EmailRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void emailAuth(EmailRequestVo emailRequestVo) {

        duplicationEmail(emailRequestVo.getEmail());

        String certificationNumber = getCertificationNumber();

        boolean isSuccess = emailProvider.sendCertificationMail(emailRequestVo.getEmail(), certificationNumber);

        if(!isSuccess){
            log.error("이메일 전송 실패");
            throw new BaseException(MASSAGE_SEND_FAILED);
        }
    }

    public static String getCertificationNumber() {
        String certificationNumber = "";

        for(int count = 0; count < 4; count++){
            certificationNumber += (int)(Math.random() * 10);
        }

        return certificationNumber;
    }
}

