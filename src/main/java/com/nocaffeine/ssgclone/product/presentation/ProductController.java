package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.dto.response.AddOptionResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.ColorOptionResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.ProductResponseDto;
import com.nocaffeine.ssgclone.product.application.ProductService;
import com.nocaffeine.ssgclone.product.dto.response.SizeOptionResponseDto;
import com.nocaffeine.ssgclone.product.vo.response.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 상세 정보 조회", description = "상품 상세 정보 조회", tags = {"Product Detail"})
    @GetMapping("/{productId}")
    public CommonResponse<ProductResponseVo> getProduct(@PathVariable("productId") Long id) {
        ProductResponseDto getProductResponseDto = productService.getProduct(id);

        return CommonResponse.success("상품을 성공적으로 찾았습니다.",
                ProductResponseVo.productDtoToVo(getProductResponseDto));
    }

    @Operation(summary = "상품 별 사이즈 옵션 조회", description = "상품 별 사이즈 옵션 조회", tags = {"Product's Size Option"})
    @GetMapping("/{productId}/size")
    public CommonResponse<List<SizeOptionResponseVo>> getSizeOptions(@PathVariable("productId") Long id) {
        List<SizeOptionResponseDto> getSizeOptionResponseDto = productService.getSizeOptions(id);

        return CommonResponse.success("사이즈 옵션을 성공적으로 찾았습니다.",
                SizeOptionResponseVo.sizeOptionDtoToVo(getSizeOptionResponseDto));
    }

    @Operation(summary = "상품 별 색상 옵션 조회", description = "상품 별 색상 옵션 조회", tags = {"Product's Color Option"})
    @GetMapping("/{productId}/color")
    public CommonResponse<List<ColorOptionResponseVo>> getColorOptions(@PathVariable("productId") Long id) {
        List<ColorOptionResponseDto> getColorOptionResponseDto = productService.getColorOptions(id);

        return CommonResponse.success("색상 옵션을 성공적으로 찾았습니다.",
                ColorOptionResponseVo.colorOptionDtoToVo(getColorOptionResponseDto));
    }

    @Operation(summary = "상품 별 추가 옵션 조회", description = "상품 별 추가 옵션 조회", tags = {"Product's Add Option"})
    @GetMapping("/{productId}/add-option")
    public CommonResponse<List<AddOptionResponseVo>> getAddOptions(@PathVariable("productId") Long id) {
        List<AddOptionResponseDto> getAddOptionResponseDto = productService.getAddOptions(id);

        return CommonResponse.success("추가 옵션을 성공적으로 찾았습니다.",
                AddOptionResponseVo.addOptionDtoToVo(getAddOptionResponseDto));
    }

    @Operation(summary = "상품 검색", description = "상품 검색", tags = {"Product Search"})
    @GetMapping
    public CommonResponse<List<ProductSearchResponseVo>> getProductsBySearchTerm(@RequestParam("search") String searchKeyword) {
        List<ProductResponseDto> getProductResponseDto = productService.getSearchProducts(searchKeyword);

        return CommonResponse.success("검색어와 일치하는 상품을 성공적으로 찾았습니다.",
                ProductSearchResponseVo.productDtoToVo(getProductResponseDto));
    }
}
