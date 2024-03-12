package com.nocaffeine.ssgclone.likefolder.domain;


import com.nocaffeine.ssgclone.like.domain.BrandLike;
import com.nocaffeine.ssgclone.like.domain.CategoryLike;
import com.nocaffeine.ssgclone.like.domain.ProductLike;
import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class FolderList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Folder folder;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Member member;

    private Long categoryLike;

    private Long productLike;

    private Long brandLike;


}
