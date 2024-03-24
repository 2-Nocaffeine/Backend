package com.nocaffeine.ssgclone.category.presentation;

import com.nocaffeine.ssgclone.category.application.ProductListWithCategoryService;
import com.nocaffeine.ssgclone.category.dto.response.ProductListIdResponse;
import com.nocaffeine.ssgclone.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/")
public class ProductListWithCategoryController {

    private final ProductListWithCategoryService productListWithCategoryService;

    @GetMapping("/{largeId}/large/productlistId")
    public CommonResponse<List<ProductListIdResponse>> getProductListWithCategory(@PathVariable("largeId") Long largeId){
        List<ProductListIdResponse> productListIdResponseList = productListWithCategoryService.getProductListWithCategory(largeId);
        return CommonResponse.success("해당 카테고리의 productList를 성공적으로 찾았습니다.",productListIdResponseList);
    }
}
