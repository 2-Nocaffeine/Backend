package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.product.domain.*;
import com.nocaffeine.ssgclone.product.dto.request.AddOptionResponse;
import com.nocaffeine.ssgclone.product.dto.request.ColorOptionResponse;
import com.nocaffeine.ssgclone.product.dto.request.ProductResponse;
import com.nocaffeine.ssgclone.product.dto.request.SizeOptionResponse;
import com.nocaffeine.ssgclone.product.infrastructure.ProductOptionRepository;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service// 서비스는 컨트롤러에서 받은 요청을 처리하고, 결과를 다시 컨트롤러에게 반환하는 역할을 한다.
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;


    @Override
    public ResponseDto<ProductResponse> getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){ // Optional 객체가 비어있는지 확인하는 메소드
            ProductResponse productResponseDto = convertToDto(product.get());
            return ResponseDto.success("제품을 찾았습니다.", productResponseDto);
        }
        return ResponseDto.fail("제품을 찾을 수 없습니다.", "ID가 " + id + "인 제품을 찾을 수 없습니다.");
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
    public ResponseDto<List<SizeOptionResponse>> getSizeOptions(Long id) {
        List<ProductOption> productOptions = productOptionRepository.findByProductId(id);

        if(productOptions.isEmpty()){
            return ResponseDto.fail("사이즈 옵션을 찾을 수 없습니다.", "ID가 " + id + "인 제품의 사이즈 옵션을 찾을 수 없습니다.");
        }

        List<SizeOptionResponse> sizeOptions = productOptions.stream()
                .map(ProductOption::getSizeOption)
                .map(this::convertToDto)
                .filter(Objects::nonNull)
                .distinct() // 중복 제거
                .collect(Collectors.toList());

        if(sizeOptions.isEmpty()){
            return ResponseDto.fail("사이즈 옵션을 가지지 않는 상품", "ID가 " + id + "인 제품은 사이즈 옵션을 가지지 않습니다.");
        }

        return ResponseDto.success("사이즈 옵션을 찾았습니다.", sizeOptions);
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
    public ResponseDto<List<ColorOptionResponse>> getColorOptions(Long id) {
        List<ProductOption> productOptions = productOptionRepository.findByProductId(id);

        if(productOptions.isEmpty()){
            return ResponseDto.fail("색상 옵션을 찾을 수 없습니다.", "ID가 " + id + "인 제품의 색상 옵션을 찾을 수 없습니다.");
        }

        List<ColorOptionResponse> colorOptions = productOptions.stream()
                .map(ProductOption::getColorOption)
                .map(this::convertToDto)
                .filter(Objects::nonNull)
                .distinct() // 중복 제거
                .collect(Collectors.toList());

        if(colorOptions.isEmpty()){
            return ResponseDto.fail("색상 옵션을 가지지 않는 상품", "ID가 " + id + "인 제품은 색상 옵션을 가지지 않습니다.");
        }

        return ResponseDto.success("색상 옵션을 찾았습니다.", colorOptions);
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
    public ResponseDto<List<AddOptionResponse>> getAddOptions(Long id) {
        List<ProductOption> productOptions = productOptionRepository.findByProductId(id);

        if(productOptions.isEmpty()){
            return ResponseDto.fail("추가 옵션을 찾을 수 없습니다.", "ID가 " + id + "인 제품의 추가 옵션을 찾을 수 없습니다.");
        }

        List<AddOptionResponse> addOptions = productOptions.stream()
                .map(ProductOption::getAddOption)
                .map(this::convertToDto)
                .filter(Objects::nonNull)
                .distinct() // 중복 제거
                .collect(Collectors.toList());

        if(addOptions.isEmpty()){
            return ResponseDto.fail("추가 옵션을 가지지 않는 상품", "ID가 " + id + "인 제품은 추가 옵션을 가지지 않습니다.");
        }

        return ResponseDto.success("추가 옵션을 찾았습니다.", addOptions);
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
