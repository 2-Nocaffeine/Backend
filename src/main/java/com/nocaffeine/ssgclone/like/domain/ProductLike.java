package com.nocaffeine.ssgclone.like.domain;

import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProductLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Member member;

    @Column(name ="folder_id")
    private Long folder;
}
