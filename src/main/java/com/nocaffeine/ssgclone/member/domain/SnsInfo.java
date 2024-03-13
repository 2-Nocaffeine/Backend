package com.nocaffeine.ssgclone.member.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class SnsInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String snsType;

    @NotNull
    private String name;

    private String profile;

}
