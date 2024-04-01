package com.nocaffeine.ssgclone.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailAuth {

    // 만료시간 (분)
    private static final Long MAX_EXPIRE_TIME = 1L;

    @Id @GeneratedValue
    private Long id;

    private String email;
    private String authCode;
    private LocalDateTime expireDate;

    @Builder
    public EmailAuth(String email, String authCode, LocalDateTime expireDate) {
        this.email = email;
        this.authCode = authCode;
        this.expireDate = LocalDateTime.now().plusMinutes(MAX_EXPIRE_TIME);
    }

    public void updateAuthCode(String authNumber) {
        this.authCode = authNumber;
        this.expireDate = LocalDateTime.now().plusMinutes(MAX_EXPIRE_TIME);
    }
}
