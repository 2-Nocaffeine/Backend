package com.nocaffeine.ssgclone.brandstore.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

    private Long brandId;
    private String brandName;
}
