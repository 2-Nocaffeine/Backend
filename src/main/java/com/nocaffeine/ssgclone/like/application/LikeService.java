package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.request.LikeProductRequest;

public interface LikeService {

    void addProductLike(LikeProductRequest likeProductRequest, String memberUuid);
}
