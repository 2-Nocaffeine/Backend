package com.nocaffeine.ssgclone.like.domain;

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

    @Column(name = "large_category_id")
    private Long largeCategory;

    @Column(name = "medium_category_id")
    private Long mediumCategory;

    @Column(name = "small_category_id")
    private Long smallCategory;

    @Column(name = "tiny_category_id")
    private Long tinyCategory;

    @Column(name = "folder_id")
    private Long folder;
}
