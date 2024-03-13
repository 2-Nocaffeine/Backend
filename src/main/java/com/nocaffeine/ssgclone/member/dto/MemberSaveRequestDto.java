package com.nocaffeine.ssgclone.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberSaveRequestDto {

    private String email;

    private String password;


    private String name;

    private String phoneNumber;
    private String address;

    private boolean isDuplicationEmail;



}

