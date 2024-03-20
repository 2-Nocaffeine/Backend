package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.common.exception.BaseResponseStatus;
import com.nocaffeine.ssgclone.product.domain.*;
import com.nocaffeine.ssgclone.product.dto.response.AddOptionResponse;
import com.nocaffeine.ssgclone.product.dto.response.ColorOptionResponse;
import com.nocaffeine.ssgclone.product.dto.response.ProductResponse;
import com.nocaffeine.ssgclone.product.dto.response.SizeOptionResponse;
import com.nocaffeine.ssgclone.product.infrastructure.ProductOptionRepository;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service// 서비스는 컨트롤러에서 받은 요청을 처리하고, 결과를 다시 컨트롤러에게 반환하는 역할을 한다.
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        return convertToDto(product);
    }

    private ProductResponse convertToDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .content(product.getContent())
                .discount(product.getDiscount())
                .build();
    }

    @Override
    public List<SizeOptionResponse> getSizeOptions(Long id) {
        List<ProductOption> productOptions = Optional.ofNullable(productOptionRepository.findByProductId(id))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BaseException(NO_EXISTING_PRODUCT));

        return productOptions.stream()
                .map(ProductOption::getSizeOption)
                .map(this::convertToDto)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    private SizeOptionResponse convertToDto(SizeOption sizeOption) {
        if (sizeOption == null) {
            return null;
        }
        return SizeOptionResponse.builder()
                .id(sizeOption.getId())
                .size(sizeOption.getSize())
                .build();
    }

    @Override
    public List<ColorOptionResponse> getColorOptions(Long id) {
        List<ProductOption> productOptions = Optional.ofNullable(productOptionRepository.findByProductId(id))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new BaseException(NO_EXISTING_PRODUCT));

        return productOptions.stream()
                .map(ProductOption::getColorOption)
                .map(this::convertToDto)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    private ColorOptionResponse convertToDto(ColorOption colorOption) {
        if (colorOption == null) {
            return null;
        }
        return ColorOptionResponse.builder()
                .id(colorOption.getId())
                .color(colorOption.getColor())
                .build();
    }

    @Override
    public List<AddOptionResponse> getAddOptions(Long id) {
        List<ProductOption> productOptions = Optional.ofNullable(productOptionRepository.findByProductId(id))
                .filter(List -> !List.isEmpty())
                .orElseThrow(() -> new BaseException(NO_EXISTING_PRODUCT));


        return productOptions.stream()
                .map(ProductOption::getAddOption)
                .map(this::convertToDto)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    private AddOptionResponse convertToDto(AddOption addOption) {
        if (addOption == null) {
            return null;
        }
        return AddOptionResponse.builder()
                .id(addOption.getId())
                .optionName(addOption.getOptionName())
                .build();
    }
}
