package com.nocaffeine.ssgclone.brandstore.presentation;

import com.nocaffeine.ssgclone.brandstore.application.BrandService;
import com.nocaffeine.ssgclone.brandstore.dto.BrandResponse;
import com.nocaffeine.ssgclone.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class BrandController {

    private final BrandService brandService;
    @GetMapping("/name/{productId}")
    public CommonResponse<BrandResponse> brandList(@PathVariable Long productId){
        BrandResponse brandResponse = brandService.findBrand(productId);
        return CommonResponse.success("브랜드를 성공적으로 찾았습니다.",brandResponse);
    }
}
