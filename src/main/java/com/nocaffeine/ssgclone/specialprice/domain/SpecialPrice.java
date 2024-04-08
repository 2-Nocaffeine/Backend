package com.nocaffeine.ssgclone.specialprice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Getter
public class SpecialPrice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 255)
    private String name;

    @NotNull
    private Timestamp startAt;

    @NotNull
    private Timestamp endAt;

    @NotNull
    @Column(length = 255)
    private String specialImageUrl;
}
