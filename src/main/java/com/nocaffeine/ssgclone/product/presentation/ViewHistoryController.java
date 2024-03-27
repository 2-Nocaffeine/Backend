package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.product.application.ViewHistoryService;
import com.nocaffeine.ssgclone.product.dto.ViewHistoryDto;
import com.nocaffeine.ssgclone.product.vo.response.ViewHistoryResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-view-history")
@Slf4j
public class ViewHistoryController {

    private final ViewHistoryService viewHistoryService;
    private final JwtTokenProvider jwtTokenProvider;

    // 최근 본 상품 목록을 조회하는 메소드
    @GetMapping
    public CommonResponse<List<ViewHistoryResponseVo>> getViewHistory() {
        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);
        List<ViewHistoryDto> viewHistory = viewHistoryService.getViewHistory(memberUuid);
        List<ViewHistoryResponseVo> getViewHistory = ViewHistoryResponseVo.viewHistoryDtoToVo(viewHistory);
        return CommonResponse.success("최근 본 상품 목록을 성공적으로 가져왔습니다.", getViewHistory);
    }

}
