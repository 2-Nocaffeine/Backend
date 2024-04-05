package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.request.ProductLikeAddRequest;
import com.nocaffeine.ssgclone.like.dto.request.ProductLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.response.ProductLikeListResponse;

import java.util.List;

public interface ProductLikeService {

    void addProductLike(ProductLikeAddRequest productLikeAddRequest, String memberUuid);
    void removeProductLike(ProductLikeRemoveRequest productLikeRemoveRequest, String memberUuid);
    List<ProductLikeListResponse> findProductLike(String memberUuid);
    Boolean isProductLike(Long productId, String memberUuid);
}
