package com.nocaffeine.ssgclone.like.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Folder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
}
