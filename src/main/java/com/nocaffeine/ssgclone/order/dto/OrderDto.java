package com.nocaffeine.ssgclone.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderDto {

    private Long id;
    private Long member;
    private String regoin;
    private String name;
    private String phoneNumber;
    private String email;
    private int totalPrice;
    private Timestamp orderDate;
    private int status;
}
