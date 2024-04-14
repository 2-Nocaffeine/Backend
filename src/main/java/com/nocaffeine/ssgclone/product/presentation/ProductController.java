package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.ProductService;
import com.nocaffeine.ssgclone.product.dto.response.category.ProductCategoryResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.AddOptionResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.ColorOptionResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.ProductOptionTypesResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.SizeOptionResponseDto;
import com.nocaffeine.ssgclone.product.vo.response.category.ProductCategoryResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.product.*;
import com.nocaffeine.ssgclone.product.vo.response.productoption.AddOptionResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.productoption.ColorOptionResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.productoption.ProductOptionTypesResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.productoption.SizeOptionResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product", description = "상품")
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    // 전체 상품 리스트 조회
    @Operation(summary = "전체 상품 리스트 조회", description = "전체 상품 리스트 조회")
    @GetMapping("/product-list")
    public CommonResponse<List<ProductListResponseVo>> getAllProducts() {

        List<ProductResponseDto> getProductListResponseDto = productService.getAllProductId();

        return CommonResponse.success("상품 리스트를 성공적으로 찾았습니다.",
                ProductListResponseVo.productDtoToVo(getProductListResponseDto));
    }

    // 전체 상품 리스트 페이징 조회
    @Operation(summary = "전체 상품 리스트 페이징 조회", description = "전체 상품 리스트 페이징 조회")
    @GetMapping("/product-list-paged")
    public CommonResponse<ProductPageListResponseVo> getAllProducts(
            @PageableDefault(size = 20, sort = "id") Pageable page) {

        ProductPageListResponseDto productPageListResponseDto = productService.getAllProducts(page);

        return CommonResponse.success("상품 리스트를 성공적으로 찾았습니다.",
                ProductPageListResponseVo.fromProductPageListResponseDto(productPageListResponseDto));
    }

    @Operation(summary = "상품 상세 정보 조회", description = "상품 상세 정보 조회")
    @GetMapping("/{productId}")
    public CommonResponse<ProductResponseVo> getProduct(
            @PathVariable("productId") Long id) {

        ProductResponseDto getProductResponseDto = productService.getProduct(id);

        return CommonResponse.success("상품을 성공적으로 찾았습니다.",
                ProductResponseVo.productDtoToVo(getProductResponseDto));
    }

    @Operation(summary = "상품 별 사이즈 옵션 조회", description = "상품 별 사이즈 옵션 조회")
    @GetMapping("/{productId}/size")
    public CommonResponse<List<SizeOptionResponseVo>> getSizeOptions(
            @PathVariable("productId") Long id) {

        List<SizeOptionResponseDto> getSizeOptionResponseDto = productService.getSizeOptions(id);

        return CommonResponse.success("사이즈 옵션을 성공적으로 찾았습니다.",
                SizeOptionResponseVo.sizeOptionDtoToVo(getSizeOptionResponseDto));
    }

    @Operation(summary = "상품 별 색상 옵션 조회", description = "상품 별 색상 옵션 조회")
    @GetMapping("/{productId}/color")
    public CommonResponse<List<ColorOptionResponseVo>> getColorOptions(
            @PathVariable("productId") Long id) {

        List<ColorOptionResponseDto> getColorOptionResponseDto = productService.getColorOptions(id);

        return CommonResponse.success("색상 옵션을 성공적으로 찾았습니다.",
                ColorOptionResponseVo.colorOptionDtoToVo(getColorOptionResponseDto));
    }

    @Operation(summary = "상품 별 추가 옵션 조회", description = "상품 별 추가 옵션 조회")
    @GetMapping("/{productId}/add-option")
    public CommonResponse<List<AddOptionResponseVo>> getAddOptions(
            @PathVariable("productId") Long id) {

        List<AddOptionResponseDto> getAddOptionResponseDto = productService.getAddOptions(id);

        return CommonResponse.success("추가 옵션을 성공적으로 찾았습니다.",
                AddOptionResponseVo.addOptionDtoToVo(getAddOptionResponseDto));
    }

    @Operation(summary = "상품 검색", description = "상품 검색")
    @GetMapping("/search")
    public CommonResponse<List<ProductSearchResponseVo>> getProductsBySearchTerm(
            @RequestParam("search") String searchKeyword) {

        List<ProductResponseDto> getProductResponseDto = productService.getSearchProducts(searchKeyword);

        return CommonResponse.success("검색어와 일치하는 상품을 성공적으로 찾았습니다.",
                ProductSearchResponseVo.productDtoToVo(getProductResponseDto));
    }

    @Operation(summary = "상품 검색 페이징", description = "상품 검색 페이징")
    @GetMapping("/search-paged")
    public CommonResponse<ProductSearchPageListResponseVo> getProductsBySearchKeyword (
            @RequestParam("search") String searchKeyword,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable page) {

        ProductPageListResponseDto getProductPageListResponseDto = productService.getSearchProductByKeyword(searchKeyword, page);

        return CommonResponse.success("검색어와 일치하는 상품을 성공적으로 찾았습니다.",
                ProductSearchPageListResponseVo.fromProductPageListResponseDto(getProductPageListResponseDto));
    }

    // 해당 상품이 어떤 옵션을 가지고 있는지 조회하는 메소드
    @Operation(summary = "상품 옵션 종류 조회", description = "상품 옵션 종류 조회")
    @GetMapping("/{productId}/option-types")
    public CommonResponse<ProductOptionTypesResponseVo> getOptionTypes(
            @PathVariable("productId") Long id) {

        ProductOptionTypesResponseDto productOptionTypesResponseDto = productService.getOptionTypes(id);

        return CommonResponse.success("상품의 옵션 종류를 성공적으로 찾았습니다.",
                ProductOptionTypesResponseVo.productOptionTypesDtoToVo(productOptionTypesResponseDto));
    }

    @Operation(summary = "상품 별 카테고리 조회", description = "상품 별 카테고리 조회")
    @GetMapping("/{productId}/category")
    public CommonResponse<ProductCategoryResponseVo> getCategory(
            @PathVariable("productId") Long id) {

        ProductCategoryResponseDto productCategoryResponseDto = productService.getCategory(id);

        return CommonResponse.success("상품의 카테고리를 성공적으로 찾았습니다.",
                ProductCategoryResponseVo.productCategoryDtoToVo(productCategoryResponseDto));
    }
}
