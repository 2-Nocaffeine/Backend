package com.nocaffeine.ssgclone.category.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class MediumCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private LargeCategory largeCategory;
}
