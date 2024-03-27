package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.ProductLikeDto;
import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import com.nocaffeine.ssgclone.like.dto.LikeProductListDto;

import java.util.List;

public interface LikeFolderService {

    void addLikeFolder(LikeFolderDto likeFolderDto, String memberUuid);
    void removeLikeFolder(LikeFolderDto likeFolderDto, String memberUuid);

    List<LikeFolderDto> findLikeFolderList(String memberUuid);
    void modifyLikeFolder(LikeFolderDto likeFolderDto, String memberUuid);

    void addProductLike(ProductLikeDto productLikeDto, String memberUuid);
    List<LikeProductListDto> findProductList(Long likeFolderId, String memberUuid);
}

