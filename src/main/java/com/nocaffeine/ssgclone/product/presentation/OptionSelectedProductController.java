package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.OptionSelectedProductService;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import com.nocaffeine.ssgclone.product.dto.OptionSelectedProductDto;
import com.nocaffeine.ssgclone.product.vo.response.OptionSelectedProductResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/option-selected-product")
@Slf4j
public class OptionSelectedProductController {

    private final OptionSelectedProductService optionSelectedProductService;

    @GetMapping("/{option-selected-product-id}")
    public CommonResponse<OptionSelectedProductResponseVo> getOptionSelectedProduct(@PathVariable("option-selected-product-id") Long id) {
        OptionSelectedProductDto getOptionSelectedProductDto = optionSelectedProductService.getOptionSelectedProduct(id);
        OptionSelectedProductResponseVo getOptionSelectedProduct = OptionSelectedProductResponseVo.optionSelectedProductDtoToVo(getOptionSelectedProductDto);
        return CommonResponse.success("옵션 선택 완료 최종 상품을 성공적으로 찾았습니다.", getOptionSelectedProduct);
    } // 옵션 선택 완료 최종 상품을 조회하는 메소드

    @GetMapping
    public CommonResponse<OptionSelectedProductResponseVo> getOptionSelectedProductByProductIdAndOptions(
            @RequestParam("product") Long productId,
            @RequestParam("size") Long sizeOptionId,
            @RequestParam("color") Long colorOptionId,
            @RequestParam("addOption") Long addOptionId) {

        OptionSelectedProductDto optionSelectedProductDto = optionSelectedProductService.getOptionSelectedProductByProductIdAndOptions(productId, sizeOptionId, colorOptionId, addOptionId);
        OptionSelectedProductResponseVo responseVo = OptionSelectedProductResponseVo.optionSelectedProductDtoToVo(optionSelectedProductDto);

        return CommonResponse.success("옵션 선택 완료 최종 상품을 성공적으로 찾았습니다.", responseVo);
    } // 상품 ID와 옵션 ID를 통해 옵션 선택 완료 최종 상품을 조회하는 메소드
}
