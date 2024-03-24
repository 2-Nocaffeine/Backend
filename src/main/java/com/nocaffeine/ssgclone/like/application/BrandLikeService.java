package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.request.BrandLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.request.BrandLikeAddRequest;

public interface BrandLikeService {

    void addBrandLike(BrandLikeAddRequest brandLikeAddRequest, String memberUuid);
    void removeBrandLike(BrandLikeRemoveRequest brandLikeRemoveRequest, String memberUuid);
}
