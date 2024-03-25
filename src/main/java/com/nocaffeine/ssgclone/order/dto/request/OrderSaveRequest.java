package com.nocaffeine.ssgclone.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderSaveRequest {

    private String regoin;
    private String name;
    private String phoneNumber;
    private String email;

}
