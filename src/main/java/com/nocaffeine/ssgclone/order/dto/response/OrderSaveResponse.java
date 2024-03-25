package com.nocaffeine.ssgclone.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderSaveResponse {

    private Long id;
    private String regoin;
    private String name;
    private String phoneNumber;
    private String email;
    private int totalPrice;
    private Timestamp orderDate;
    private String status;
}
