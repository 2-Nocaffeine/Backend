package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.ProductImageService;
import com.nocaffeine.ssgclone.product.dto.response.ProductImageResponseDto;
import com.nocaffeine.ssgclone.product.vo.response.ProductImageListResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.ProductThumbnailResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product", description = "상품")
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductImageController {

    private final ProductImageService productImageService;

    // 특정 상품의 이미지 리스트 조회
    @Operation(summary = "상품 이미지 리스트 조회", description = "상품 이미지 리스트 조회")
    @GetMapping("/{productId}/image")
    public CommonResponse<List<ProductImageListResponseVo>> getProductImageList(@PathVariable("productId") Long id) {
        List<ProductImageResponseDto> productImageResponseDto = productImageService.getProductImageList(id);

        return CommonResponse.success("상품 이미지 리스트를 성공적으로 불러왔습니다.", ProductImageListResponseVo.productImageDtoToVo(productImageResponseDto));
    }

    // 특정 상품의 썸네일 이미지 조회
    @Operation(summary = "상품 썸네일 이미지 조회", description = "상품 썸네일 이미지 조회")
    @GetMapping("/{productId}/thumbnail")
    public CommonResponse<ProductThumbnailResponseVo> getProductThumbnail(@PathVariable("productId") Long id) {
        List<ProductImageResponseDto> productImageResponseDto = productImageService.getProductImageList(id);

        return CommonResponse.success("상품 썸네일 이미지를 성공적으로 불러왔습니다.", ProductThumbnailResponseVo.productImageDtoToVo(productImageResponseDto));
    }
}
