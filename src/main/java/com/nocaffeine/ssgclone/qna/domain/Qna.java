package com.nocaffeine.ssgclone.qna.domain;

import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Qna {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @NotNull
    private Long member;

    @NotNull
    private int type;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private int status;
}
