package com.nocaffeine.ssgclone.cart.domain;

import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.product.domain.ProductOption;
import jakarta.persistence.*;

@Entity
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOption productOption;

    private int quantity;
    private boolean pin;
    private boolean checkProduct;
}
