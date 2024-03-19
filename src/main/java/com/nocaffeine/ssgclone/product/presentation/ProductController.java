package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.dto.response.AddOptionResponse;
import com.nocaffeine.ssgclone.product.dto.response.ColorOptionResponse;
import com.nocaffeine.ssgclone.product.dto.response.ProductResponse;
import com.nocaffeine.ssgclone.product.application.ProductService;
import com.nocaffeine.ssgclone.product.dto.response.SizeOptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public CommonResponse<ProductResponse> getProduct(@PathVariable Long id) {
        return CommonResponse.success("Product retrieved successfully", productService.getProduct(id));
    }

//    @GetMapping("/{id}/size")
//    public CommonResponse<List<SizeOptionResponse>> getSizeOptions(@PathVariable Long id) {
//        return productService.getSizeOptions(id);
//    }
//
//    @GetMapping("/{id}/color")
//    public CommonResponse<List<ColorOptionResponse>> getColorOptions(@PathVariable Long id) {
//        return productService.getColorOptions(id);
//    }
//
//    @GetMapping("/{id}/add")
//    public CommonResponse<List<AddOptionResponse>> getAddOptions(@PathVariable Long id) {
//        return productService.getAddOptions(id);
//    }

}
