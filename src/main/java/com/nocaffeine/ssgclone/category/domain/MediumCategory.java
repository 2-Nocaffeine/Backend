package com.nocaffeine.ssgclone.category.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MediumCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private LargeCategory largeCategory;

    @Builder
    public MediumCategory(String name, LargeCategory largeCategory) {
        this.name = name;
        this.largeCategory = largeCategory;
    }
}
