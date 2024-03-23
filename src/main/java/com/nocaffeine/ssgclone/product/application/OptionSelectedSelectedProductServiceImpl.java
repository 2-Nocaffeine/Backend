package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import com.nocaffeine.ssgclone.product.dto.response.OptionSelectedProductResponse;
import com.nocaffeine.ssgclone.product.infrastructure.OptionSelectedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_SELECTED_OPTION_PRODUCT;

@Service// 서비스는 컨트롤러에서 받은 요청을 처리하고, 결과를 다시 컨트롤러에게 반환하는 역할을 한다.
@RequiredArgsConstructor
public class OptionSelectedSelectedProductServiceImpl implements OptionSelectedProductService {

    private final OptionSelectedProductRepository optionSelectedProductRepository;

    @Override
    public OptionSelectedProductResponse getProductOptionSelected(Long id) {
        OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findById(id)
                .orElseThrow(() -> new BaseException(NO_SELECTED_OPTION_PRODUCT));

        return convertToDto(optionSelectedProduct);
    }

    private OptionSelectedProductResponse convertToDto(OptionSelectedProduct optionSelectedProduct) {
        return OptionSelectedProductResponse.builder()
                .product(optionSelectedProduct.getProduct())
                .sizeOption(optionSelectedProduct.getSizeOption())
                .colorOption(optionSelectedProduct.getColorOption())
                .addOption(optionSelectedProduct.getAddOption())
                .stock(optionSelectedProduct.getStock())
                .build();
    }

}
