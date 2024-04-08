package com.nocaffeine.ssgclone.specialprice.application;
import com.nocaffeine.ssgclone.specialprice.domain.SpecialPrice;
import com.nocaffeine.ssgclone.specialprice.domain.SpecialPriceList;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceDetailResponseDto;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceIdListResponseDto;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceInfoResponseDto;
import com.nocaffeine.ssgclone.specialprice.dto.response.SpecialPriceProductIdResponseDto;
import com.nocaffeine.ssgclone.specialprice.infrastructure.SpecialPriceListRepository;
import com.nocaffeine.ssgclone.specialprice.infrastructure.SpecialPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SpecialPriceServiceImp implements SpecialPriceService{

    private final SpecialPriceRepository specialPriceRepository;
    private final SpecialPriceListRepository specialPriceListRepository;

    @Override
    public List<SpecialPriceIdListResponseDto> findSpecialPriceIds() {

        List<SpecialPriceIdListResponseDto> specialPriceIdListResponseDtos = new ArrayList<>();

        //특가id 리스트
        for (SpecialPrice specialPrice : specialPriceRepository.findAll()){
            SpecialPriceIdListResponseDto specialPriceIdListResponseDto = SpecialPriceIdListResponseDto.builder()
                            .specialId(specialPrice.getId())
                            .build();
            specialPriceIdListResponseDtos.add(specialPriceIdListResponseDto);
        }
        return specialPriceIdListResponseDtos;}


    @Override
    public SpecialPriceInfoResponseDto findSpecialPriceInfo(Long specialPriceId) {

        //최소 가격 찾기
        int minPrice = specialPriceListRepository.findMinPriceBySpecialPriceId(specialPriceId);

        //썸네일 찾기
        Optional<SpecialPrice> thumbnailUrl = specialPriceRepository.findById(specialPriceId);


        return SpecialPriceInfoResponseDto.builder()
                .lowestPrice(minPrice)
                .thumbnailUrl(thumbnailUrl.get().getSpecialImageUrl())
                .build();
        }

    @Override
    public SpecialPriceDetailResponseDto findSpecialPriceProductList(Long specialPriceId) {

        List<SpecialPriceProductIdResponseDto> specialPriceProductIdResponseDtos = new ArrayList<>();

        //특가별 상품id 리스트
        for (SpecialPriceList specialPriceList : specialPriceListRepository.findBySpecialPriceId(specialPriceId)){
            SpecialPriceProductIdResponseDto specialPriceProductIdResponseDto = SpecialPriceProductIdResponseDto.builder()
                            .productId(specialPriceList.getProduct().getId())
                            .build();
            specialPriceProductIdResponseDtos.add(specialPriceProductIdResponseDto);
        }

        //최소 가격 찾기
        int minPrice = specialPriceListRepository.findMinPriceBySpecialPriceId(specialPriceId);

        return SpecialPriceDetailResponseDto.builder()
                .specialPriceName(specialPriceRepository.findById(specialPriceId).get().getName())
                .lowestPrice(minPrice)
                .thumbnailUrl(specialPriceRepository.findById(specialPriceId).get().getSpecialImageUrl())
                .specialPriceProductList(specialPriceProductIdResponseDtos)
                .build();

    }
}