package com.nocaffeine.ssgclone.member.application;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.dto.request.MemberLoginRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberPasswordRequestDto;
import com.nocaffeine.ssgclone.member.dto.request.MemberSaveRequestDto;
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

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

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
    public CommonResponse<Void> duplicationEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new BaseException(DUPLICATE_EMAIL);
        }
        return CommonResponse.success("duplicationEmail success");
    }

    /**
     * 회원가입
     */
    @Override
    @Transactional
    public CommonResponse<Void> signUp(MemberSaveRequestDto memberSaveRequestDto) {

        try{
            duplicationEmail(memberSaveRequestDto.getEmail());
        } catch (BaseException e){
            throw new BaseException(DUPLICATE_EMAIL);
        }
        createMember(memberSaveRequestDto);

        return CommonResponse.success("CreateMember success");
    }

    /**
     * 로그인
     */
    @Override
    public ResponseEntity<CommonResponse<Object>> logIn(MemberLoginRequestDto memberLoginRequestDto) {
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
        log.info("token: {}", token);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", token);

        return ResponseEntity.ok().headers(httpHeaders)
                .body(CommonResponse.success("login success", token));
    }

    /**
     * 비밀번호 변경
     */
    @Override
    @Transactional
    public CommonResponse<Void> changePassword(String memberUuid, MemberPasswordRequestDto memberPasswordRequestDto) {
        // 회원 정보 조회
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        if(!memberPasswordRequestDto.password.equals(memberPasswordRequestDto.getPasswordCheck())){
            throw new BaseException(FAILED_TO_PASSWORD);
        }

        // 비밀번호 변경
        member.changeHashPassword(memberPasswordRequestDto.getPassword());

        return CommonResponse.success("비밀번호를 변경했습니다.");
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


    private String createToken(Member member) {
        return jwtTokenProvider.generateToken(member);
    }
}

