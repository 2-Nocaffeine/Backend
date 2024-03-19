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
        return CommonResponse.success("상품을 성공적으로 찾았습니다.", productService.getProduct(id));
    }

    @GetMapping("/{id}/size")
    public CommonResponse<List<SizeOptionResponse>> getSizeOptions(@PathVariable Long id) {
        List<SizeOptionResponse> getSizeOptions = productService.getSizeOptions(id);
        return CommonResponse.success("사이즈 옵션을 성공적으로 찾았습니다.", getSizeOptions);
    }

    @GetMapping("/{id}/color")
    public CommonResponse<List<ColorOptionResponse>> getColorOptions(@PathVariable Long id) {
        List<ColorOptionResponse> colorOptions = productService.getColorOptions(id);
        return CommonResponse.success("색상 옵션을 성공적으로 찾았습니다.", colorOptions);
    }

    @GetMapping("/{id}/add")
    public CommonResponse<List<AddOptionResponse>> getAddOptions(@PathVariable Long id) {
        List<AddOptionResponse> addOptions = productService.getAddOptions(id);
        return CommonResponse.success("추가 옵션을 성공적으로 찾았습니다.", addOptions);
    }

}
