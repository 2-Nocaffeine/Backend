package com.nocaffeine.ssgclone.member.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnsInfo extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String snsType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String snsId;

    @Builder
    public SnsInfo(String snsType, Member member, String snsId) {
        this.snsType = snsType;
        this.member = member;
        this.snsId = snsId;
    }
}
