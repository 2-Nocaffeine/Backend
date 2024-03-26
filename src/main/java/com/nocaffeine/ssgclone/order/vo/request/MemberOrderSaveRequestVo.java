package com.nocaffeine.ssgclone.order.vo.request;

import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberOrderSaveRequestVo {

    private List<Long> optionSelectedProducts;
    private String region;
    private String name;
    private String phoneNumber;
    private String email;
    private int totalPrice;

}
