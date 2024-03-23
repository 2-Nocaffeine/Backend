package com.nocaffeine.ssgclone.like.domain;

import com.nocaffeine.ssgclone.brandstore.domain.Brand;
import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class BrandLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Brand brand;

    @Column(name = "like_folder_id")
    private Long likeFolder;
}
