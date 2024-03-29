package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.ViewHistoryDto;

import java.util.List;

public interface ViewHistoryService {

    // 최근 본 상품을 조회하는 메소드
    List<ViewHistoryDto> getViewHistory(String memberUuid);

    // 최근 본 상품을 추가하는 메소드
    void saveViewHistory(String memberUuid, Long productId);
//
//    // 최근 본 상품을 삭제하는 메소드
//    void deleteViewHistory(String memberUuid, Long productId);
}
