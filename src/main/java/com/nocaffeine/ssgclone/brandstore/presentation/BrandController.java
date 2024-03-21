package com.nocaffeine.ssgclone.brandstore.presentation;

import com.nocaffeine.ssgclone.brandstore.application.BrandService;
import com.nocaffeine.ssgclone.brandstore.dto.Response.BrandListResponse;
import com.nocaffeine.ssgclone.common.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;

//    @GetMapping("/api/v1/product/brand/{product_id}")
//    public CommonResponse<List<BrandListResponse>> findBrandName(@PathVariable("product_id") Long product_id){
//        List<BrandListResponse> brandListResponseList = brandService.findBrandName(product_id);
//        return CommonResponse.success("성공", brandListResponseList);
//    }
}
