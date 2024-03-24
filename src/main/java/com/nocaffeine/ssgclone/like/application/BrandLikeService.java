package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.request.LikeBrandAddRequest;

public interface BrandLikeService {

    void addBrandLike(LikeBrandAddRequest likeBrandAddRequest, String memberUuid);
}
