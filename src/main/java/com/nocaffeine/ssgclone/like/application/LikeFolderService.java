package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.ProductLikeAddDto;
import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import com.nocaffeine.ssgclone.like.dto.ProductLikeListDto;
import com.nocaffeine.ssgclone.like.dto.ProductLikeRemoveDto;

import java.util.List;

public interface LikeFolderService {

    void addLikeFolder(LikeFolderDto likeFolderDto, String memberUuid);
    void removeLikeFolder(Long likeFolderId, String memberUuid);
    List<LikeFolderDto> findLikeFolder(String memberUuid);
    void modifyLikeFolder(LikeFolderDto likeFolderDto, String memberUuid);

    void addProductLike(ProductLikeAddDto productLikeAddDto, String memberUuid);
    List<ProductLikeListDto> findProductLike(Long likeFolderId, String memberUuid);
    void removeProductLike(Long likeFolderId, List<Long> productLikeId, String memberUuid);
}

