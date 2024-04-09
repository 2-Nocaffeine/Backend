package com.nocaffeine.ssgclone.product.dto.response.productoption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionTypesResponseDto {

    private Long productId;
    private Long colorOptionId;
    private Long sizeOptionId;
    private Long addOptionId;
}
