package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.TotalService;
import com.nocaffeine.ssgclone.product.dto.response.total.ProductTotalResponseDto;
import com.nocaffeine.ssgclone.product.vo.response.total.ProductTotalResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product")
@RequestMapping("/api/v1/product")
@Slf4j
public class TotalController {

    private final TotalService totalService;

    @Operation(summary = "상품의 집계 조회" , description = "상품의 집계 조회")
    @GetMapping("/{productId}/total")
    public CommonResponse<ProductTotalResponseVo> findProductTotal(@PathVariable("productId") Long productId) {
        return CommonResponse.success("집계 조회 성공", ProductTotalResponseDto.dtoToVo(totalService.findProductTotal(productId)));
    }

}
