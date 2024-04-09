package com.nocaffeine.ssgclone.product.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductIdListResponseDto {

    private Long id;
    private Long productId;

}
