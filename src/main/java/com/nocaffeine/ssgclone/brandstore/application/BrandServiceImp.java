package com.nocaffeine.ssgclone.brandstore.application;

import com.nocaffeine.ssgclone.brandstore.dto.Response.BrandListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BrandServiceImp implements BrandService {

//    @Override
//    public List<BrandListResponse> findBrandName(Long productId) {
//
//    }
}
