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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_DATA;

@Service// 서비스는 컨트롤러에서 받은 요청을 처리하고, 결과를 다시 컨트롤러에게 반환하는 역할을 한다.
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    @Transactional
    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BaseException(NO_DATA));

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

//    @Transactional
//    @Override
//    public CommonResponse<List<SizeOptionResponse>> getSizeOptions(Long id) {
//        List<ProductOption> productOptions = productOptionRepository.findByProductId(id);
//
//        if(productOptions.isEmpty()){
//            return CommonResponse.fail("사이즈 옵션을 찾을 수 없습니다.", "ID가 " + id + "인 제품의 사이즈 옵션을 찾을 수 없습니다.");
//        }
//
//        List<SizeOptionResponse> sizeOptions = productOptions.stream()
//                .map(ProductOption::getSizeOption)
//                .map(this::convertToDto)
//                .filter(Objects::nonNull)
//                .distinct() // 중복 제거
//                .collect(Collectors.toList());
//
//        if(sizeOptions.isEmpty()){
//            return CommonResponse.fail("사이즈 옵션을 가지지 않는 상품", "ID가 " + id + "인 제품은 사이즈 옵션을 가지지 않습니다.");
//        }
//
//        return CommonResponse.success("사이즈 옵션을 찾았습니다.", sizeOptions);
//    }
//
//    private SizeOptionResponse convertToDto(SizeOption sizeOption) {
//        if (sizeOption == null) {
//            return null;
//        }
//        return SizeOptionResponse.builder()
//                .id(sizeOption.getId())
//                .size(sizeOption.getSize())
//                .build();
//    }
//
//    @Transactional
//    @Override
//    public CommonResponse<List<ColorOptionResponse>> getColorOptions(Long id) {
//        List<ProductOption> productOptions = productOptionRepository.findByProductId(id);
//
//        if(productOptions.isEmpty()){
//            return CommonResponse.fail("색상 옵션을 찾을 수 없습니다.", "ID가 " + id + "인 제품의 색상 옵션을 찾을 수 없습니다.");
//        }
//
//        List<ColorOptionResponse> colorOptions = productOptions.stream()
//                .map(ProductOption::getColorOption)
//                .map(this::convertToDto)
//                .filter(Objects::nonNull)
//                .distinct() // 중복 제거
//                .collect(Collectors.toList());
//
//        if(colorOptions.isEmpty()){
//            return CommonResponse.fail("색상 옵션을 가지지 않는 상품", "ID가 " + id + "인 제품은 색상 옵션을 가지지 않습니다.");
//        }
//
//        return CommonResponse.success("색상 옵션을 찾았습니다.", colorOptions);
//    }
//
//    private ColorOptionResponse convertToDto(ColorOption colorOption) {
//        if (colorOption == null) {
//            return null;
//        }
//        return ColorOptionResponse.builder()
//                .id(colorOption.getId())
//                .color(colorOption.getColor())
//                .build();
//    }
//
//    @Transactional
//    @Override
//    public CommonResponse<List<AddOptionResponse>> getAddOptions(Long id) {
//        List<ProductOption> productOptions = productOptionRepository.findByProductId(id);
//
//        if(productOptions.isEmpty()){
//            return CommonResponse.fail("추가 옵션을 찾을 수 없습니다.", "ID가 " + id + "인 제품의 추가 옵션을 찾을 수 없습니다.");
//        }
//
//        List<AddOptionResponse> addOptions = productOptions.stream()
//                .map(ProductOption::getAddOption)
//                .map(this::convertToDto)
//                .filter(Objects::nonNull)
//                .distinct() // 중복 제거
//                .collect(Collectors.toList());
//
//        if(addOptions.isEmpty()){
//            return CommonResponse.fail("추가 옵션을 가지지 않는 상품", "ID가 " + id + "인 제품은 추가 옵션을 가지지 않습니다.");
//        }
//
//        return CommonResponse.success("추가 옵션을 찾았습니다.", addOptions);
//    }
//
//    private AddOptionResponse convertToDto(AddOption addOption) {
//        if (addOption == null) {
//            return null;
//        }
//        return AddOptionResponse.builder()
//                .id(addOption.getId())
//                .optionName(addOption.getOptionName())
//                .build();
//    }
}
