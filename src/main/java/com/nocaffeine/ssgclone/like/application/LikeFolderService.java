package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.like.dto.LikeFolderDto;
import com.nocaffeine.ssgclone.like.vo.request.LikeFolderAddVo;

public interface LikeFolderService {

    void addLikeFolder(LikeFolderDto likeFolderDto, String memberUuid);

}

