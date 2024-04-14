package com.nocaffeine.ssgclone.deliveryaddress.domain;

import com.nocaffeine.ssgclone.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
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


    @Builder
    public DeliveryAddress(Long id, Member member, String addressName, String recipient, String phoneNumber, String zip, String post, String street, boolean defaultCheck) {
        this.id = id;
        this.member = member;
        this.addressName = addressName;
        this.recipient = recipient;
        this.phoneNumber = phoneNumber;
        this.zip = zip;
        this.post = post;
        this.street = street;
        this.defaultCheck = defaultCheck;
    }
}
