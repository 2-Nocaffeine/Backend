package com.nocaffeine.ssgclone.member.dto.request;


import com.nocaffeine.ssgclone.member.vo.request.SnsMemberLoginRequestVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnsMemberLoginRequestDto {
    private String snsId;
    private String snsType;
    private String email;

    public static SnsMemberLoginRequestDto voToDto(SnsMemberLoginRequestVo snsMemberLoginRequestVo) {
        return SnsMemberLoginRequestDto.builder()
                .snsId(snsMemberLoginRequestVo.getSnsId())
                .snsType(snsMemberLoginRequestVo.getSnsType())
                .email(snsMemberLoginRequestVo.getEmail())
                .build();




    }
}
