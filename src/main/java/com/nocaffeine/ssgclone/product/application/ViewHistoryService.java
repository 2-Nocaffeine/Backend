package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.ViewHistoryDto;
import com.nocaffeine.ssgclone.product.dto.ViewHistoryListDto;

import java.util.List;

public interface ViewHistoryService {

    // 최근 본 상품을 조회하는 메소드
    List<ViewHistoryDto> getViewHistory(String memberUuid);

    // 최근 본 상품을 추가하는 메소드
    void addViewHistory(String memberUuid, ViewHistoryDto viewHistoryDto);

    // 최근 본 상품을 삭제하는 메소드\
    void removeViewHistorys(String memberUuid, ViewHistoryListDto viewHistoryListDto);
}
