package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.product.application.ViewHistoryService;
import com.nocaffeine.ssgclone.product.dto.request.viewhistory.ViewHistoryRequestDto;
import com.nocaffeine.ssgclone.product.dto.response.viewhistory.ViewHistoryPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.viewhistory.ViewHistoryResponseDto;
import com.nocaffeine.ssgclone.product.dto.request.viewhistory.ViewHistoryListRequestDto;
import com.nocaffeine.ssgclone.product.vo.request.viewhistory.ViewHistoryDeleteRequestVo;
import com.nocaffeine.ssgclone.product.vo.request.viewhistory.ViewHistoryRequestVo;
import com.nocaffeine.ssgclone.product.vo.response.viewhistory.ViewHistoryPageListResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.viewhistory.ViewHistoryResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "View History", description = "최근 기록")
@RequestMapping("/api/v1/product-view-history")
@Slf4j
public class ViewHistoryController {

    private final ViewHistoryService viewHistoryService;
    private final JwtTokenProvider jwtTokenProvider;

    // 최근 본 상품 목록을 조회하는 메소드
    @Operation(summary = "최근 본 상품 목록 조회", description = "최근 본 상품 목록 조회", tags = {"View History"})
    @GetMapping("/list")
    public CommonResponse<List<ViewHistoryResponseVo>> getViewHistory() {

        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        List<ViewHistoryResponseDto> viewHistory = viewHistoryService.getViewHistory(memberUuid);

        return CommonResponse.success("최근 본 상품 목록을 성공적으로 가져왔습니다.",
                ViewHistoryResponseVo.viewHistoryDtoToVo(viewHistory));
    }

    // 최근 본 상품 조회
    @Operation(summary = "최근 본 상품 목록 조회", description = "최근 본 상품 목록 조회", tags = {"View History"})
    @GetMapping
    public CommonResponse<ViewHistoryPageListResponseVo> getViewHistoryPageList(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable page) {

        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        ViewHistoryPageListResponseDto viewHistoryPageListResponseDto = viewHistoryService.getViewHistoryPageList(memberUuid, page);

        return CommonResponse.success("최근 본 상품 목록을 성공적으로 가져왔습니다.",
                ViewHistoryPageListResponseVo.fromViewHistoryPageListResponseDto(viewHistoryPageListResponseDto));
    }

    // 최근 본 상품을 추가하는 메소드
    @Operation(summary = "최근 본 상품 추가", description = "최근 본 상품 추가", tags = {"View History"})
    @PostMapping
    public CommonResponse<Void> addViewHistory(
            @RequestBody ViewHistoryRequestVo viewHistoryRequestVo) {

        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        viewHistoryService.addViewHistory(memberUuid, ViewHistoryRequestDto.viewHistoryVoToDto(viewHistoryRequestVo));

        return CommonResponse.success("최근 본 상품 조회 이력이 성공적으로 저장되었습니다.");
    }

    // 최근 본 상품을 리스트의 형태로 받아 삭제하는 메소드
    @Operation(summary = "최근 본 상품 삭제", description = "최근 본 상품 삭제", tags = {"View History"})
    @DeleteMapping
    public CommonResponse<List<Void>> deleteViewHistoryProducts(
            @RequestBody ViewHistoryDeleteRequestVo viewHistoryDeleteRequestVo) {

        String token = jwtTokenProvider.getHeader();
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(token);

        viewHistoryService.removeViewHistorys(memberUuid, ViewHistoryListRequestDto.viewHistoryVoToDto(viewHistoryDeleteRequestVo));

        return CommonResponse.success("최근 본 상품 조회 이력이 성공적으로 삭제되었습니다.");
    }
}
