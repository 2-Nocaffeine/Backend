package com.nocaffeine.ssgclone.brandstore.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandListResponse {
    private long product_id;
    private long brand_id;
    private String brand_name;
}
