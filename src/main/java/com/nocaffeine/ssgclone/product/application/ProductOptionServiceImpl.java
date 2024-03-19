package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.common.exception.BaseResponseStatus;
import com.nocaffeine.ssgclone.product.domain.ProductOption;
import com.nocaffeine.ssgclone.product.dto.response.ProductOptionResponse;
import com.nocaffeine.ssgclone.product.infrastructure.ProductOptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_SELECTED_OPTION_PRODUCT;

@Service// 서비스는 컨트롤러에서 받은 요청을 처리하고, 결과를 다시 컨트롤러에게 반환하는 역할을 한다.
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

    private final ProductOptionRepository productOptionRepository;

    @Override
    public ProductOptionResponse getProductOptionSelected(Long id) {
        ProductOption productOption = productOptionRepository.findById(id)
                .orElseThrow(() -> new BaseException(NO_SELECTED_OPTION_PRODUCT));

        return convertToDto(productOption);
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
