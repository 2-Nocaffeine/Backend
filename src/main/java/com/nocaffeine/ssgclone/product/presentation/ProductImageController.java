package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.product.application.ProductImageService;
import com.nocaffeine.ssgclone.product.vo.response.ProductImageListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductImageController {

    private final ProductImageService productImageService;

    // 특정 상품의 이미지 리스트 조회
//    @Operation(summary = "상품 이미지 리스트 조회", description = "상품 이미지 리스트 조회", tags = {"Product's Image"})
//    @GetMapping("/{productId}/image")
//    public ResponseEntity<List<ProductImageListResponseVo>> getProductImageList(@PathVariable("productId") Long id) {
//
//    }

    // 특정 상품의 썸네알 이미지 조회
}
