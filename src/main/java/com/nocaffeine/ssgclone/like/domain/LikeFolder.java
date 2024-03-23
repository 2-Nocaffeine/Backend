package com.nocaffeine.ssgclone.like.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class LikeFolder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;
}
