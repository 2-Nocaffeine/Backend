package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import com.nocaffeine.ssgclone.product.dto.OptionSelectedProductDto;
import com.nocaffeine.ssgclone.product.infrastructure.OptionSelectedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_EXISTING_PRODUCT;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_SELECTED_OPTION_PRODUCT;

@Service// 서비스는 컨트롤러에서 받은 요청을 처리하고, 결과를 다시 컨트롤러에게 반환하는 역할을 한다.
@RequiredArgsConstructor
public class OptionSelectedProductServiceImpl implements OptionSelectedProductService {

    private final OptionSelectedProductRepository optionSelectedProductRepository;

    @Override
    public OptionSelectedProductDto getOptionSelectedProduct(Long id) {
        OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findById(id)
                .orElseThrow(() -> new BaseException(NO_SELECTED_OPTION_PRODUCT));

        return OptionSelectedProductDto.fromOptionSelectedProduct(optionSelectedProduct);
    }

    @Override
    public OptionSelectedProductDto getOptionSelectedProductByProductIdAndOptions(Long productId, Long sizeOptionId, Long colorOptionId, Long addOptionId) {
        OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findByProductIdAndSizeOptionIdAndColorOptionIdAndAddOptionId(productId, sizeOptionId, colorOptionId, addOptionId)
                .orElseThrow(() -> new BaseException(NO_SELECTED_OPTION_PRODUCT));

        return OptionSelectedProductDto.fromOptionSelectedProduct(optionSelectedProduct);
    }

}
