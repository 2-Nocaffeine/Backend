package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.request.LikeProductAddRequest;
import com.nocaffeine.ssgclone.like.dto.request.LikeProductRemoveRequest;

public interface LikeService {

    void addProductLike(LikeProductAddRequest likeProductAddRequest, String memberUuid);
    void removeProductLike(LikeProductRemoveRequest likeProductRemoveRequest, String memberUuid);
}
