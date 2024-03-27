package com.nocaffeine.ssgclone.order.vo.request;

import com.nocaffeine.ssgclone.order.dto.OrderedProductDto;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberOrderProductRequestVo {

    private List<OrderedProductVo> orderProducts;
    private String region;
    private String name;
    private String phoneNumber;
    private String email;
    private int totalPrice;

}
