package com.nocaffeine.ssgclone.brandstore.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponseDto {

    private Long brandId;
    private String brandName;
}
