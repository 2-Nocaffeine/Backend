package com.nocaffeine.ssgclone.product.application;


import com.nocaffeine.ssgclone.product.dto.response.total.ProductTotalResponseDto;

import java.util.List;

public interface TotalService {

    ProductTotalResponseDto findProductTotal(Long productId);


}
