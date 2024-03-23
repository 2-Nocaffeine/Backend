package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.response.OptionSelectedProductResponse;

public interface OptionSelectedProductService {

    // 옵션이 선택되어 최종적으로 구매할 수 있는 상품을 조회하는 메소드
    OptionSelectedProductResponse getProductOptionSelected(Long id);


}
