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

        //특가 id 리스트
        for (SpecialPrice specialPrice : specialPriceRepository.findAll()){
            SpecialPriceIdListResponseDto specialPriceIdListResponseDto = SpecialPriceIdListResponseDto.builder()
                            .specialId(specialPrice.getId())
                            .build();
            specialPriceIdListResponseDtos.add(specialPriceIdListResponseDto);
        }
        return specialPriceIdListResponseDtos;}


    @Override
    public SpecialPriceInfoResponseDto findSpecialPriceInfo(Long specialPriceId) {

        SpecialPrice specialPrice = specialPriceRepository.findById(specialPriceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 특가가 없습니다."));

        //최소 가격 찾기
        int minPrice = specialPriceListRepository.findMinPriceBySpecialPriceId(specialPriceId);


        return SpecialPriceInfoResponseDto.builder()
                .title(specialPrice.getName())
                .lowestPrice(minPrice)
                .thumbnailUrl(specialPrice.getSpecialImageUrl())
                .subTitle(specialPrice.getSubTitle())
                .build();
        }

    @Override
    public SpecialPriceDetailResponseDto findSpecialPriceProductList(Long specialPriceId) {

        SpecialPrice specialPrice = specialPriceRepository.findById(specialPriceId)
                .orElseThrow(() -> new IllegalArgumentException("해당 특가가 없습니다."));

        List<SpecialPriceProductIdResponseDto> specialPriceProductIdResponseDtos = new ArrayList<>();

        //특가별 상품 id 리스트
        for (SpecialPriceList specialPriceList : specialPriceListRepository.findBySpecialPriceId(specialPriceId)){
            SpecialPriceProductIdResponseDto specialPriceProductIdResponseDto = SpecialPriceProductIdResponseDto.builder()
                            .productId(specialPriceList.getProduct().getId())
                            .build();
            specialPriceProductIdResponseDtos.add(specialPriceProductIdResponseDto);
        }

        //최소 가격 찾기
        int minPrice = specialPriceListRepository.findMinPriceBySpecialPriceId(specialPriceId);

        return SpecialPriceDetailResponseDto.builder()
                .specialPriceName(specialPrice.getName())
                .lowestPrice(minPrice)
                .thumbnailUrl(specialPrice.getSpecialImageUrl())
                .specialPriceProductList(specialPriceProductIdResponseDtos)
                .build();

    }

    @Override
    public List<SpecialPriceProductIdResponseDto> findSpecialPriceRandomId() {

        List<SpecialPriceList> specialPriceLists = specialPriceListRepository.findAll();
        List<SpecialPriceProductIdResponseDto> specialPriceProductIdResponseDtos = new ArrayList<>();

        for (SpecialPriceList specialPriceList : specialPriceLists){
            SpecialPriceProductIdResponseDto specialPriceProductIdResponseDto = SpecialPriceProductIdResponseDto.builder()
                    .productId(specialPriceList.getProduct().getId())
                    .build();
            specialPriceProductIdResponseDtos.add(specialPriceProductIdResponseDto);
        }
        return specialPriceProductIdResponseDtos;
    }
}