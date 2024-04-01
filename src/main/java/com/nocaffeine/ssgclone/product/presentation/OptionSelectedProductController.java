package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.OptionSelectedProductService;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import com.nocaffeine.ssgclone.product.dto.OptionSelectedProductDto;
import com.nocaffeine.ssgclone.product.vo.response.OptionSelectedProductResponseVo;
import io.swagger.v3.oas.annotations.Operation;
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

        return CommonResponse.success("옵션 선택 완료 최종 상품을 성공적으로 찾았습니다.",
                OptionSelectedProductResponseVo.optionSelectedProductDtoToVo(getOptionSelectedProductDto));
    } // 옵션 선택 완료 최종 상품을 조회하는 메소드

    @Operation(summary = "상품 ID와 옵션 ID를 통해 옵션 선택 완료 최종 상품을 조회", description = "상품 ID와 옵션 ID를 통해 옵션 선택 완료 최종 상품을 조회", tags = {"Option Selected Product"})
    @GetMapping
    public CommonResponse<OptionSelectedProductResponseVo> getOptionSelectedProductByProductIdAndOptions(
            @RequestParam("product") Long productId,
            @RequestParam("size") Long sizeOptionId,
            @RequestParam("color") Long colorOptionId,
            @RequestParam("addOption") Long addOptionId) {

        OptionSelectedProductDto optionSelectedProductDto = optionSelectedProductService.getOptionSelectedProductByProductIdAndOptions(productId, sizeOptionId, colorOptionId, addOptionId);

        return CommonResponse.success("옵션 선택 완료 최종 상품을 성공적으로 찾았습니다.",
                OptionSelectedProductResponseVo.optionSelectedProductDtoToVo(optionSelectedProductDto));
    } // 상품 ID와 옵션 ID를 통해 옵션 선택 완료 최종 상품을 조회하는 메소드
}
