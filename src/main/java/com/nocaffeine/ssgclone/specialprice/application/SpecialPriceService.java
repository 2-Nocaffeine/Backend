package com.nocaffeine.ssgclone.specialprice.application;
import com.nocaffeine.ssgclone.specialprice.dto.response.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface SpecialPriceService {
    List<SpecialPriceIdListResponseDto> findSpecialPriceIds();

    SpecialPriceInfoResponseDto findSpecialPriceInfo(Long specialPriceId);

    SpecialPriceDetailResponseDto findSpecialPriceProductList(Long specialPriceId);

    SpecialPriceProductPageListResponseDto findSpecialPriceRandomIdPaged(Pageable page);
}