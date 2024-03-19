package com.nocaffeine.ssgclone.member.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDetailResponse {
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
}
