package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.OptionSelectedProductService;
import com.nocaffeine.ssgclone.product.dto.OptionSelectedProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/option-selected-product")
@Slf4j
public class OptionSelectedProductController {

    private final OptionSelectedProductService optionSelectedProductService;

    @GetMapping("/{option-selected-product-id}")
    public CommonResponse<OptionSelectedProductDto> getOptionSelectedProduct(@PathVariable("option-selected-product-id") Long id) {
        OptionSelectedProductDto getOptionSelectedProduct = optionSelectedProductService.getProductOptionSelected(id);
        return CommonResponse.success("상품 옵션을 성공적으로 찾았습니다.", getOptionSelectedProduct);
    }
}
