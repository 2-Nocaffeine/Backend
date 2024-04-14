package com.nocaffeine.ssgclone.like.domain;

import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CategoryLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Member member;

    @Column(name = "medium_category_id")
    private Long mediumCategory;

    @Column(name = "small_category_id")
    private Long smallCategory;

    @Column(name = "tiny_category_id")
    private Long tinyCategory;

    @Column(name = "like_folder_id")
    private Long likeFolder;

    @Builder
    public CategoryLike(Member member, Long mediumCategory, Long smallCategory, Long tinyCategory, Long likeFolder) {
        this.member = member;
        this.mediumCategory = mediumCategory;
        this.smallCategory = smallCategory;
        this.tinyCategory = tinyCategory;
        this.likeFolder = likeFolder;
    }
}
