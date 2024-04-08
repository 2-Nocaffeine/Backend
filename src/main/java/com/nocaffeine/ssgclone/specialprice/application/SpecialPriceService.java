package com.nocaffeine.ssgclone.specialprice.application;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceDetailResponseDto;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceIdListResponseDto;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceInfoResponseDto;
import java.util.List;
public interface SpecialPriceService {
    List<SpecialPriceIdListResponseDto> findSpecialPriceIds();

    SpecialPriceInfoResponseDto findSpecialPriceInfo(Long specialPriceId);

    SpecialPriceDetailResponseDto findSpecialPriceProductList(Long specialPriceId);
}