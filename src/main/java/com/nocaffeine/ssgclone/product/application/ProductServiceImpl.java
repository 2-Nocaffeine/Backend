package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.category.domain.ProductCategoryList;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.product.domain.*;
import com.nocaffeine.ssgclone.product.dto.response.category.ProductCategoryResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.AddOptionResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.ColorOptionResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.ProductOptionTypesResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.SizeOptionResponseDto;
import com.nocaffeine.ssgclone.product.infrastructure.OptionSelectedProductRepository;
import com.nocaffeine.ssgclone.product.infrastructure.ProductCategoryListRepository;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service// 서비스는 컨트롤러에서 받은 요청을 처리하고, 결과를 다시 컨트롤러에게 반환하는 역할을 한다.
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final OptionSelectedProductRepository optionSelectedProductRepository;
    private final ProductCategoryListRepository productCategoryListRepository;

    // 전체 상품 리스트를 가져오는 메소드
    @Override
    public List<ProductResponseDto> getAllProductId() {

        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> responses = new ArrayList<>();

        for (Product product : products) {
            responses.add(ProductResponseDto.fromProduct(product));
        }

        return responses;
    }


    // 전체 상품 리스트를 10개씩 페이징을 해서 가져오는 메소드
    @Override
    public ProductPageListResponseDto getAllProducts(Pageable page) {

        Page<Product> products = productRepository.findAll(page);

        List<ProductResponseDto> responses = new ArrayList<>();

        for (Product product : products) {
            responses.add(ProductResponseDto.fromProduct(product));
        }

        return ProductPageListResponseDto.fromProductPageListResponseDto(products.getNumber(), products.getTotalPages(), products.hasNext(), products.isLast(), responses);
    }


    @Override
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        return ProductResponseDto.fromProduct(product);
    }

    @Override
    public List<SizeOptionResponseDto> getSizeOptions(Long id) {
        List<OptionSelectedProduct> optionSelectedProducts = Optional.ofNullable(optionSelectedProductRepository.findByProductId(id))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BaseException(NO_EXISTING_PRODUCT));

        List<SizeOptionResponseDto> responses = new ArrayList<>();

        for (OptionSelectedProduct optionSelectedProduct : optionSelectedProducts) {

            SizeOption sizeOption = optionSelectedProduct.getSizeOption();

            SizeOptionResponseDto response = SizeOptionResponseDto.builder()
                    .id(sizeOption.getId())
                    .size(sizeOption.getSize())
                    .build();

            if (!responses.contains(response)) {
                responses.add(response);
            } // 중복 제거
        }
        return responses;
    }

    @Override
    public List<ColorOptionResponseDto> getColorOptions(Long id) {
        List<OptionSelectedProduct> optionSelectedProducts = Optional.ofNullable(optionSelectedProductRepository.findByProductId(id))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BaseException(NO_EXISTING_PRODUCT));

        List<ColorOptionResponseDto> responses = new ArrayList<>();

        for (OptionSelectedProduct optionSelectedProduct : optionSelectedProducts) {

            ColorOption colorOption = optionSelectedProduct.getColorOption();

            ColorOptionResponseDto response = ColorOptionResponseDto.builder()
                    .id(colorOption.getId())
                    .color(colorOption.getColor())
                    .build();

            if (!responses.contains(response)) {
                responses.add(response);
            } // 중복 제거
        }

        return responses;
    }

    @Override
    public List<AddOptionResponseDto> getAddOptions(Long id) {
        List<OptionSelectedProduct> optionSelectedProducts = Optional.ofNullable(optionSelectedProductRepository.findByProductId(id))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BaseException(NO_EXISTING_PRODUCT));

        List<AddOptionResponseDto> responses = new ArrayList<>();

        for (OptionSelectedProduct optionSelectedProduct : optionSelectedProducts) {

            AddOption addOption = optionSelectedProduct.getAddOption();

            AddOptionResponseDto response = AddOptionResponseDto.builder()
                    .id(addOption.getId())
                    .optionName(addOption.getOptionName())
                    .build();

            if (!responses.contains(response)) {
                responses.add(response);
            } // 중복 제거
        }

        return responses;
    }

    @Override
    public List<ProductResponseDto> getSearchProducts(String keyword) {
        List<Product> products = productRepository.findByNameContaining(keyword);

        List<ProductResponseDto> responses = new ArrayList<>();

        for (Product product : products) {
            responses.add(ProductResponseDto.fromProduct(product));
        }

        return responses;
    }

    @Override
    public ProductPageListResponseDto getSearchProductByKeyword(String searchKeyword, Pageable page) {

        Page<Product> products = productRepository.findByNameContaining(searchKeyword, page);

        List<ProductResponseDto> responses = new ArrayList<>();

        for (Product product : products) {
            responses.add(ProductResponseDto.fromProduct(product));
        }

        return ProductPageListResponseDto.fromProductPageListResponseDto(products.getNumber(), products.getTotalPages(), products.hasNext(), products.isLast(), responses);
    }

    @Override
    public ProductOptionTypesResponseDto getOptionTypes(Long id) {
        List<OptionSelectedProduct> optionSelectedProducts = Optional.ofNullable(optionSelectedProductRepository.findByProductId(id))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        List<ProductOptionTypesResponseDto> responses = new ArrayList<>();

        for (OptionSelectedProduct optionSelectedProduct : optionSelectedProducts) {

            ProductOptionTypesResponseDto response = ProductOptionTypesResponseDto.builder()
                    .colorOptionId(optionSelectedProduct.getColorOption().getId())
                    .sizeOptionId(optionSelectedProduct.getSizeOption().getId())
                    .addOptionId(optionSelectedProduct.getAddOption().getId())
                    .build();

            if (!responses.contains(response)) {
                responses.add(response);
            } // 중복 제거
        }

        return responses.get(0);
    }

    @Override
    public ProductCategoryResponseDto getCategory(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        ProductCategoryList productCategoryList = productCategoryListRepository.findByProduct(product);

        return ProductCategoryResponseDto.fromProductCategoryList(productCategoryList);
    }
}
