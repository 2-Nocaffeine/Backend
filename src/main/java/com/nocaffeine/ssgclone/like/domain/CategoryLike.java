package com.nocaffeine.ssgclone.like.domain;

import com.nocaffeine.ssgclone.category.domain.LargeCategory;
import com.nocaffeine.ssgclone.category.domain.MediumCategory;
import com.nocaffeine.ssgclone.category.domain.SmallCategory;
import com.nocaffeine.ssgclone.category.domain.TinyCategory;
import com.nocaffeine.ssgclone.likefolder.domain.Folder;
import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class CategoryLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Member member;

    private Long largeCategory;

    private Long mediumCategory;

    private Long smallCategory;

    private Long tinyCategory;

    private Long folder;
}
