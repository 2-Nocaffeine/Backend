package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.product.application.ViewHistoryService;
import com.nocaffeine.ssgclone.product.dto.ViewHistoryDto;
import com.nocaffeine.ssgclone.product.dto.ViewHistoryListDto;
import com.nocaffeine.ssgclone.product.vo.request.ViewHistoryDeleteRequestVo;
import com.nocaffeine.ssgclone.product.vo.request.ViewHistoryRequestVo;
import com.nocaffeine.ssgclone.product.vo.response.ViewHistoryResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    // 최근 본 상품을 추가하는 메소드
    @PostMapping
    public CommonResponse<Void> addViewHistory(@RequestBody ViewHistoryRequestVo viewHistoryRequestVo) {
        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        ViewHistoryDto viewHistoryDto = ViewHistoryDto.viewHistoryVoToDto(viewHistoryRequestVo);

        viewHistoryService.addViewHistory(memberUuid, viewHistoryDto);

        return CommonResponse.success("최근 본 상품 조회 이력이 성공적으로 저장되었습니다.");
    }

    // 최근 본 상품을 리스트의 형태로 받아 삭제하는 메소드
    @DeleteMapping
    public CommonResponse<List<Void>> deleteViewHistoryProducts(@RequestBody ViewHistoryDeleteRequestVo viewHistoryDeleteRequestVo) {
        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        ViewHistoryListDto viewHistoryDto = ViewHistoryListDto.viewHistoryVoToDto(viewHistoryDeleteRequestVo);

        viewHistoryService.removeViewHistorys(memberUuid, viewHistoryDto);
        return CommonResponse.success("최근 본 상품 조회 이력이 성공적으로 삭제되었습니다.");
    }
}
