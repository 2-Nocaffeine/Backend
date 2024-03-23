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
public class SmallCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private MediumCategory mediumCategory;

    @Builder
    public SmallCategory(String name, MediumCategory mediumCategory) {
        this.name = name;
        this.mediumCategory = mediumCategory;
    }

}
