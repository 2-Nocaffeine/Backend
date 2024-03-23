package com.nocaffeine.ssgclone.member.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class SnsInfo extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String snsType;

    @NotNull
    @Column(length = 50)
    private String name;

    @Column(length = 255)
    private String profile;

}
