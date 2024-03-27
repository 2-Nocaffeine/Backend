package com.nocaffeine.ssgclone.like.domain;

import com.nocaffeine.ssgclone.brandstore.domain.Brand;
import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public BrandLike(Member member, Brand brand, Long likeFolder) {
        this.member = member;
        this.brand = brand;
        this.likeFolder = likeFolder;
    }
}
