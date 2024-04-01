package com.nocaffeine.ssgclone.cart.vo.request;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CartRemoveRequestVo {
    private List<Long> cartId = new ArrayList<>();

}
