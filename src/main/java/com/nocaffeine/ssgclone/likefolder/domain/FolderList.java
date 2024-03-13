package com.nocaffeine.ssgclone.likefolder.domain;

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

    @Column(name = "category_like_id")
    private Long categoryLike;

    @Column(name = "product_like_id")
    private Long productLike;

    @Column(name = "brand_like_id")
    private Long brandLike;


}
