package com.nocaffeine.ssgclone.deliveryaddress;

import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAddress {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @NotNull
    private String addressName;
    @NotNull
    private String recipient;
    @NotNull
    private String phoneNumber;
    private String zip;
    private String post;
    private String street;
    @NotNull
    private boolean defaultCheck;

}
