package com.nocaffeine.ssgclone.brandstore.application;

import com.nocaffeine.ssgclone.brandstore.domain.BrandList;
import com.nocaffeine.ssgclone.brandstore.dto.BrandResponse;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandListRepository;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImp implements BrandService{

    private final BrandListRepository brandListRepository;
    @Override
    public BrandResponse findBrandId(Long productId) {
        Optional<BrandList> brandID = brandListRepository.findById(productId);

        BrandResponse brandResponse = BrandResponse.builder()
                .BrandId(brandID.get().getBrand().getId())
                .BrandName(brandID.get().getBrand().getName())
                .build();
        return brandResponse;
    }
}
