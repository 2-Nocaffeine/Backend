package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.dto.AddOptionDto;
import com.nocaffeine.ssgclone.product.dto.ColorOptionDto;
import com.nocaffeine.ssgclone.product.dto.ProductDto;
import com.nocaffeine.ssgclone.product.application.ProductService;
import com.nocaffeine.ssgclone.product.dto.SizeOptionDto;
import com.nocaffeine.ssgclone.product.vo.response.AddOptionResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.ColorOptionResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.ProductResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.SizeOptionResponseVo;
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
    public CommonResponse<ProductResponseVo> getProduct(@PathVariable Long id) {
        ProductDto getProductDto = productService.getProduct(id);
        ProductResponseVo getProduct = ProductResponseVo.productDtoToVo(getProductDto);
        return CommonResponse.success("상품을 성공적으로 찾았습니다.", getProduct);
    }

    @GetMapping("/{id}/size")
    public CommonResponse<List<SizeOptionResponseVo>> getSizeOptions(@PathVariable Long id) {
        List<SizeOptionDto> getSizeOptionDto = productService.getSizeOptions(id);
        List<SizeOptionResponseVo> getSizeOptions = SizeOptionResponseVo.sizeOptionDtoToVo(getSizeOptionDto);
        return CommonResponse.success("사이즈 옵션을 성공적으로 찾았습니다.", getSizeOptions);
    }

    @GetMapping("/{id}/color")
    public CommonResponse<List<ColorOptionResponseVo>> getColorOptions(@PathVariable Long id) {
        List<ColorOptionDto> getColorOptionDto = productService.getColorOptions(id);
        List<ColorOptionResponseVo> getColorOptions = ColorOptionResponseVo.colorOptionDtoToVo(getColorOptionDto);
        return CommonResponse.success("색상 옵션을 성공적으로 찾았습니다.", getColorOptions);
    }

    @GetMapping("/{id}/add")
    public CommonResponse<List<AddOptionResponseVo>> getAddOptions(@PathVariable Long id) {
        List<AddOptionDto> getAddOptionDto = productService.getAddOptions(id);
        List<AddOptionResponseVo> getAddOptions = AddOptionResponseVo.addOptionDtoToVo(getAddOptionDto);
        return CommonResponse.success("추가 옵션을 성공적으로 찾았습니다.", getAddOptions);
    }

}
