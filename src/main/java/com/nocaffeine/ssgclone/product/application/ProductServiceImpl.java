package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.product.domain.*;
import com.nocaffeine.ssgclone.product.dto.AddOptionDto;
import com.nocaffeine.ssgclone.product.dto.ColorOptionDto;
import com.nocaffeine.ssgclone.product.dto.ProductDto;
import com.nocaffeine.ssgclone.product.dto.SizeOptionDto;
import com.nocaffeine.ssgclone.product.infrastructure.OptionSelectedProductRepository;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service// 서비스는 컨트롤러에서 받은 요청을 처리하고, 결과를 다시 컨트롤러에게 반환하는 역할을 한다.
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final OptionSelectedProductRepository optionSelectedProductRepository;

    @Override
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        return convertToDto(product);
    }

    private ProductDto convertToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .content(product.getContent())
                .discount(product.getDiscount())
                .build();
    }

    @Override
    public List<SizeOptionDto> getSizeOptions(Long id) {
        List<OptionSelectedProduct> optionSelectedProducts = Optional.ofNullable(optionSelectedProductRepository.findByProductId(id))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BaseException(NO_EXISTING_PRODUCT));

        List<SizeOptionDto> responses = new ArrayList<>();

        for (OptionSelectedProduct optionSelectedProduct : optionSelectedProducts) {

            SizeOption sizeOption = optionSelectedProduct.getSizeOption();

            SizeOptionDto response = SizeOptionDto.builder()
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
    public List<ColorOptionDto> getColorOptions(Long id) {
        List<OptionSelectedProduct> optionSelectedProducts = Optional.ofNullable(optionSelectedProductRepository.findByProductId(id))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BaseException(NO_EXISTING_PRODUCT));

        List<ColorOptionDto> responses = new ArrayList<>();

        for (OptionSelectedProduct optionSelectedProduct : optionSelectedProducts) {

            ColorOption colorOption = optionSelectedProduct.getColorOption();

            ColorOptionDto response = ColorOptionDto.builder()
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
    public List<AddOptionDto> getAddOptions(Long id) {
        List<OptionSelectedProduct> optionSelectedProducts = Optional.ofNullable(optionSelectedProductRepository.findByProductId(id))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BaseException(NO_EXISTING_PRODUCT));

        List<AddOptionDto> responses = new ArrayList<>();

        for (OptionSelectedProduct optionSelectedProduct : optionSelectedProducts) {

            AddOption addOption = optionSelectedProduct.getAddOption();

            AddOptionDto response = AddOptionDto.builder()
                    .id(addOption.getId())
                    .optionName(addOption.getOptionName())
                    .build();

            if (!responses.contains(response)) {
                responses.add(response);
            } // 중복 제거
        }

        return responses;
    }
}
