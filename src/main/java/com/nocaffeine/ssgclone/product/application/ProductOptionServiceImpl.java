package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.product.domain.ProductOption;
import com.nocaffeine.ssgclone.product.dto.response.ProductOptionResponse;
import com.nocaffeine.ssgclone.product.infrastructure.ProductOptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service// 서비스는 컨트롤러에서 받은 요청을 처리하고, 결과를 다시 컨트롤러에게 반환하는 역할을 한다.
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

    private final ProductOptionRepository productOptionRepository;

    @Transactional
    @Override
    public ResponseDto<ProductOptionResponse> getProductOptionSelected(Long id) {
        Optional<ProductOption> productOption = productOptionRepository.findById(id);
        if(productOption.isPresent()){
            ProductOptionResponse productOptionResponseDto = convertToDto(productOption.get());
            return ResponseDto.success("옵션 선택을 완료한 최종 구매 가능 상품을 찾았습니다.", productOptionResponseDto);
        }
        return ResponseDto.fail("옵션 선택을 완료한 최종 구매 가능 상품을 찾을 수 없습니다.", "ID가 " + id + "인 옵션 선택을 완료한 최종 구매 가능 상품을 찾을 수 없습니다.");
    }

    private ProductOptionResponse convertToDto(ProductOption productOption) {
        return ProductOptionResponse.builder()
                .product(productOption.getProduct())
                .sizeOption(productOption.getSizeOption())
                .colorOption(productOption.getColorOption())
                .addOption(productOption.getAddOption())
                .quantity(productOption.getQuantity())
                .build();
    }

}
