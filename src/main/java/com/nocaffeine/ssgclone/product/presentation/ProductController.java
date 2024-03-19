package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.ResponseDto;
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
    public ResponseDto<ProductResponse> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/{id}/size")
    public ResponseDto<List<SizeOptionResponse>> getSizeOptions(@PathVariable Long id) {
        return productService.getSizeOptions(id);
    }

    @GetMapping("/{id}/color")
    public ResponseDto<List<ColorOptionResponse>> getColorOptions(@PathVariable Long id) {
        return productService.getColorOptions(id);
    }

    @GetMapping("/{id}/add")
    public ResponseDto<List<AddOptionResponse>> getAddOptions(@PathVariable Long id) {
        return productService.getAddOptions(id);
    }

}
