package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.request.LikeProductAddRequest;
import com.nocaffeine.ssgclone.like.dto.request.LikeProductRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.response.ProductLikeListResponse;

import java.util.List;

public interface ProductLikeService {

    void addProductLike(LikeProductAddRequest likeProductAddRequest, String memberUuid);
    void removeProductLike(LikeProductRemoveRequest likeProductRemoveRequest, String memberUuid);
    List<ProductLikeListResponse> findProductLike(String memberUuid);
}
