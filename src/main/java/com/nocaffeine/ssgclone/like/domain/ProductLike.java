package com.nocaffeine.ssgclone.like.domain;

import com.nocaffeine.ssgclone.likefolder.domain.Folder;
import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;

@Entity
public class ProductLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Long folder;
}
