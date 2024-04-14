package com.nocaffeine.ssgclone.cart.domain;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.INVALID_CART_QUANTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private OptionSelectedProduct optionSelectedProduct;

    private int quantity;
    private boolean pin;
    private boolean checkProduct;

    @Builder
    public Cart(Long id, Member member, OptionSelectedProduct optionSelectedProduct, int quantity, boolean pin, boolean checkProduct) {
        this.id = id;
        this.member = member;
        this.optionSelectedProduct = optionSelectedProduct;
        this.quantity = quantity;
        this.pin = pin;
        this.checkProduct = checkProduct;
    }


    public int minusQuantity() {
        if(quantity > 1){
            this.quantity--;
        } else{
            throw new BaseException(INVALID_CART_QUANTITY);
        }
        return quantity;
    }


    public int plusQuantity() {
        this.quantity++;
        return quantity;
    }



}
