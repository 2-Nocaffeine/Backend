package com.nocaffeine.ssgclone.member.dto.response;

import com.nocaffeine.ssgclone.member.vo.response.MemberDetailResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDetailResponseDto {
    private String email;
    private String name;
    private String phoneNumber;
    private String address;


    public static MemberDetailResponseVo dtoToVo(MemberDetailResponseDto memberDetailResponseDto) {
        return new MemberDetailResponseVo(memberDetailResponseDto.getEmail(), memberDetailResponseDto.getName(),
                memberDetailResponseDto.getPhoneNumber(), memberDetailResponseDto.getAddress());


    }
}
