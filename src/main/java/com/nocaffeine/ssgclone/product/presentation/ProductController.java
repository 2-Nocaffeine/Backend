package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.dto.AddOptionDto;
import com.nocaffeine.ssgclone.product.dto.ColorOptionDto;
import com.nocaffeine.ssgclone.product.dto.ProductDto;
import com.nocaffeine.ssgclone.product.application.ProductService;
import com.nocaffeine.ssgclone.product.dto.SizeOptionDto;
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
    public CommonResponse<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto getProduct = productService.getProduct(id);
        return CommonResponse.success("상품을 성공적으로 찾았습니다.", getProduct);
    }

    @GetMapping("/{id}/size")
    public CommonResponse<List<SizeOptionDto>> getSizeOptions(@PathVariable Long id) {
        List<SizeOptionDto> getSizeOptions = productService.getSizeOptions(id);
        return CommonResponse.success("사이즈 옵션을 성공적으로 찾았습니다.", getSizeOptions);
    }

    @GetMapping("/{id}/color")
    public CommonResponse<List<ColorOptionDto>> getColorOptions(@PathVariable Long id) {
        List<ColorOptionDto> colorOptions = productService.getColorOptions(id);
        return CommonResponse.success("색상 옵션을 성공적으로 찾았습니다.", colorOptions);
    }

    @GetMapping("/{id}/add")
    public CommonResponse<List<AddOptionDto>> getAddOptions(@PathVariable Long id) {
        List<AddOptionDto> addOptions = productService.getAddOptions(id);
        return CommonResponse.success("추가 옵션을 성공적으로 찾았습니다.", addOptions);
    }

}
