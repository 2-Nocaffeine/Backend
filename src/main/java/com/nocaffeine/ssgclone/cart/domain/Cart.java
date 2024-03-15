package com.nocaffeine.ssgclone.cart.domain;

import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.product.domain.ProductOption;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    @Builder
    public Cart(Member member, ProductOption productOption, int quantity, boolean pin, boolean checkProduct) {
        this.member = member;
        this.productOption = productOption;
        this.quantity = quantity;
        this.pin = pin;
        this.checkProduct = checkProduct;
    }
}
