package com.nocaffeine.ssgclone.order.domain;

import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long member;

    @NotNull
    private String region;

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

    @NotNull
    private int totalPrice;

    @NotNull
    private Timestamp orderDate;

    @NotNull
    private int status;

}
