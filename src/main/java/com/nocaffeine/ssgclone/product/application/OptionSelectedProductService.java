package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.domain.AddOption;
import com.nocaffeine.ssgclone.product.domain.ColorOption;
import com.nocaffeine.ssgclone.product.domain.SizeOption;
import com.nocaffeine.ssgclone.product.dto.OptionSelectedProductDto;

public interface OptionSelectedProductService {

    // 옵션이 선택되어 최종적으로 구매할 수 있는 상품을 조회하는 메소드
    OptionSelectedProductDto getOptionSelectedProduct(Long id);

    // 상품 ID와 옵션 ID를 통해 옵션 선택 완료 최종 상품을 조회하는 메소드
    OptionSelectedProductDto getOptionSelectedProductByProductIdAndOptions(Long productId, Long sizeOptionId, Long colorOptionId, Long addOptionId);
}
