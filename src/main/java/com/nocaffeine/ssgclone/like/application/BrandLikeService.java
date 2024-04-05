package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.request.BrandLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.request.BrandLikeAddRequest;
import com.nocaffeine.ssgclone.like.dto.response.BrandLikeListResponse;

import java.util.List;

public interface BrandLikeService {

    void addBrandLike(BrandLikeAddRequest brandLikeAddRequest, String memberUuid);
    void removeBrandLike(BrandLikeRemoveRequest brandLikeRemoveRequest, String memberUuid);

    List<BrandLikeListResponse> findBrandLike(String memberUuid);

    boolean isBrandLike(Long brandId, String memberUuid);
}
