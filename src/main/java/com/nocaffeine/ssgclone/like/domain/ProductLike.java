package com.nocaffeine.ssgclone.like.domain;

import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @Column(name ="folder_id")
    private Long folder;


    @Builder
    public ProductLike(Member member, Product product, Long folder) {
        this.member = member;
        this.product = product;
        this.folder = folder;
    }
}
