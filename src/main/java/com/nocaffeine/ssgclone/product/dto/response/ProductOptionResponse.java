package com.nocaffeine.ssgclone.product.dto.response;

import com.nocaffeine.ssgclone.product.domain.AddOption;
import com.nocaffeine.ssgclone.product.domain.ColorOption;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.domain.SizeOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionResponse {

    private Product product;
    private SizeOption sizeOption;
    private ColorOption colorOption;
    private AddOption addOption;
    private int quantity;
}
