package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.ProductOptionService;
import com.nocaffeine.ssgclone.product.dto.response.ProductOptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-option")
@Slf4j
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    @GetMapping("/{product-option-id}")
    public CommonResponse<ProductOptionResponse> getProductOption(@PathVariable("product-option-id") Long id) {
        ProductOptionResponse getProductOption = productOptionService.getProductOptionSelected(id);
        return CommonResponse.success("상품 옵션을 성공적으로 찾았습니다.", getProductOption);
    }
}
