package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.request.BrandLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.request.LikeBrandAddRequest;

public interface BrandLikeService {

    void addBrandLike(LikeBrandAddRequest likeBrandAddRequest, String memberUuid);
    void removeBrandLike(BrandLikeRemoveRequest brandLikeRemoveRequest, String memberUuid);
}
