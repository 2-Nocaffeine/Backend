package com.nocaffeine.ssgclone.order.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import com.nocaffeine.ssgclone.order.dto.request.UserOrderSaveRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Random;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Orders extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long orderNumber;

    @Column(length = 255)
    private String uuid;

    @NotNull
    @Column(length = 255)
    private String region;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    @Column(length = 50)
    private String phoneNumber;

    @NotNull
    @Column(length = 100)
    private String email;

    @NotNull
    private int totalPrice;

//    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(length = 50)
    private OrderStatus status;


    public enum OrderStatus {
        ORDERED, PREPARING, DELIVERING, DETERMINING, CANCEL
    }

    public static Orders toEntity(UserOrderSaveRequestDto userOrderSaveRequestDto) {
        Random random = new Random();
        return Orders.builder()
                .orderNumber((long) random.nextInt(90000000) + 10000000)
                .uuid(userOrderSaveRequestDto.getUuid())
                .region(userOrderSaveRequestDto.getRegion())
                .name(userOrderSaveRequestDto.getName())
                .phoneNumber(userOrderSaveRequestDto.getPhoneNumber())
                .email(userOrderSaveRequestDto.getEmail())
                .totalPrice(userOrderSaveRequestDto.getTotalPrice())
                .status(OrderStatus.ORDERED)
                .build();
    }

}
