package com.nocaffeine.ssgclone.member.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String phoneNNumber;

    @NotNull
    private String address;


}
