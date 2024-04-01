package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.product.application.ViewHistoryService;
import com.nocaffeine.ssgclone.product.dto.ViewHistoryDto;
import com.nocaffeine.ssgclone.product.vo.request.ViewHistoryRequestVo;
import com.nocaffeine.ssgclone.product.vo.response.ViewHistoryResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.EXPIRED_AUTH_CODE;

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

        viewHistoryService.saveViewHistory(memberUuid, viewHistoryDto.getProductId());
        return CommonResponse.success("최근 본 상품 조회 이력이 성공적으로 저장되었습니다.");
    }
}
