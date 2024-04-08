package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.OptionSelectedProductService;
import com.nocaffeine.ssgclone.product.dto.response.OptionSelectedProductResponseDto;
import com.nocaffeine.ssgclone.product.vo.response.OptionSelectedProductListResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.OptionSelectedProductResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product", description = "상품")
@RequestMapping("/api/v1/option-selected-product")
@Slf4j
public class OptionSelectedProductController {

    private final OptionSelectedProductService optionSelectedProductService;

    @Operation(summary = "옵션 선택 완료 최종 상품 조회", description = "옵션 선택 완료 최종 상품 조회")
    @GetMapping("/{optionSelectedProductId}")
    public CommonResponse<OptionSelectedProductResponseVo> getOptionSelectedProduct(@PathVariable("optionSelectedProductId") Long id) {
        OptionSelectedProductResponseDto getOptionSelectedProductResponseDto = optionSelectedProductService.getOptionSelectedProduct(id);

        return CommonResponse.success("옵션 선택 완료 최종 상품을 성공적으로 찾았습니다.",
                OptionSelectedProductResponseVo.optionSelectedProductDtoToVo(getOptionSelectedProductResponseDto));
    } // 옵션 선택 완료 최종 상품을 조회하는 메소드

    @Operation(summary = "상품 ID와 옵션 ID를 통해 옵션 선택 완료 최종 상품을 조회", description = "상품 ID와 옵션 ID를 통해 옵션 선택 완료 최종 상품을 조회 [테스트용]")
    @GetMapping
    public CommonResponse<OptionSelectedProductResponseVo> getOptionSelectedProductByProductIdAndOptions(
            @RequestParam("product") Long productId,
            @RequestParam("color") Long colorOptionId,
            @RequestParam("size") Long sizeOptionId,
            @RequestParam("addOption") Long addOptionId) {

        OptionSelectedProductResponseDto optionSelectedProductResponseDto = optionSelectedProductService.getOptionSelectedProductByProductIdAndOptions(productId, sizeOptionId, colorOptionId, addOptionId);

        return CommonResponse.success("옵션 선택 완료 최종 상품을 성공적으로 찾았습니다.",
                OptionSelectedProductResponseVo.optionSelectedProductDtoToVo(optionSelectedProductResponseDto));
    } // 상품 ID와 옵션 ID를 통해 옵션 선택 완료 최종 상품을 조회하는 메소드

    @Operation(summary = "선택한 옵션으로 옵션 선택 완료 최종 상품 조회", description = "선택한 옵션으로 옵션 선택 완료 최종 상품 조회")
    @GetMapping("/selected-options-list")
    public CommonResponse<List<OptionSelectedProductListResponseVo>> getOptionSelectedProductListBySelectedOptions(
            @RequestParam("product") Long productId,
            @RequestParam(value = "color", required = false) Long colorOptionId,
            @RequestParam(value = "size", required = false) Long sizeOptionId,
            @RequestParam(value = "addOption", required = false) Long addOptionId) {

        List<OptionSelectedProductResponseDto> optionSelectedProductResponseDtoList = optionSelectedProductService.getOptionSelectedProductListBySelectedOptionsList(productId, colorOptionId, sizeOptionId, addOptionId);

        return CommonResponse.success("옵션 선택 완료 최종 상품을 성공적으로 찾았습니다.",
                OptionSelectedProductListResponseVo.optionSelectedProductDtoListToVoList(optionSelectedProductResponseDtoList));
    }
}

