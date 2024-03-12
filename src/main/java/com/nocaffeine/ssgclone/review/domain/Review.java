package com.nocaffeine.ssgclone.review.domain;

import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Member member;

    @NotNull
    private String content;

    @NotNull
    private int rate;

    @NotNull
    private Timestamp date;

}
