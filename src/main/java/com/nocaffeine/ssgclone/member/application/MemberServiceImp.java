package com.nocaffeine.ssgclone.member.application;

import com.nocaffeine.ssgclone.common.ResponseDto;
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
    public ResponseDto<Void> signUp(MemberSaveRequestDto memberSaveRequestDto) {

        try{
            duplicationEmail(memberSaveRequestDto.getEmail());
        } catch (BaseException e){
            throw new BaseException("아이디 중복체크를 해주세요.");
        }
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
        httpHeaders.add("Authorization", token);

        return ResponseEntity.ok().headers(httpHeaders)
                .body(ResponseDto.success("login success", token));
    }

    /**
     * 비밀번호 변경
     */
    @Override
    @Transactional
    public ResponseDto<Void> changePassword(String memberUuid, MemberPasswordRequestDto memberPasswordRequestDto) {
        // 회원 정보 조회
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException("회원을 찾을 수 없습니다."));

        if(!memberPasswordRequestDto.password.equals(memberPasswordRequestDto.getPasswordCheck())){
            throw new BaseException("비밀번호를 다시 한번 확인 해 주세요.");
        }

        // 비밀번호 변경
        member.changeHashPassword(memberPasswordRequestDto.getPassword());

        return ResponseDto.success("비밀번호를 변경했습니다.");
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

