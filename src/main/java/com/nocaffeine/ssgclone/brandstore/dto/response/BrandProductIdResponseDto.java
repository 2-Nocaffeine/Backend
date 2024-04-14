package com.nocaffeine.ssgclone.brandstore.dto.response;

import com.nocaffeine.ssgclone.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandProductIdResponseDto {

    private Long productId;

    public static BrandProductIdResponseDto fromBrandProductIdResponseDto(Product product) {
        return BrandProductIdResponseDto.builder()
                .productId(product.getId())
                .build();
    }
}
