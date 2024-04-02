package com.nocaffeine.ssgclone.member.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnsMemberAddDto {
    private String snsId;
    private String snsType;
    private String email;

    private String name;
    private String phoneNumber;

}
