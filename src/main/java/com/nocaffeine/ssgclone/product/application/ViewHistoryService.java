package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.request.ViewHistoryRequestDto;
import com.nocaffeine.ssgclone.product.dto.response.ViewHistoryPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.ViewHistoryResponseDto;
import com.nocaffeine.ssgclone.product.dto.request.ViewHistoryListRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ViewHistoryService {

    // 최근 본 상품을 조회하는 메소드
    List<ViewHistoryResponseDto> getViewHistory(String memberUuid);

    // 최근 본 상품을 추가하는 메소드
    void addViewHistory(String memberUuid, ViewHistoryRequestDto viewHistoryRequestDto);

    // 최근 본 상품을 삭제하는 메소드\
    void removeViewHistorys(String memberUuid, ViewHistoryListRequestDto viewHistoryListRequestDto);

    // 최근 본 상품을 조회하는 메소드
    ViewHistoryPageListResponseDto getViewHistoryPageList(String memberUuid, Pageable page);
}
