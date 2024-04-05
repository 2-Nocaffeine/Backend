package com.nocaffeine.ssgclone.review.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.review.application.ReviewService;
import com.nocaffeine.ssgclone.review.dto.request.ReviewAddRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewModifyRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewRemoveRequestDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewDetailResponseDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewImageResponseDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewListResponseDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewPossibleWriteResponseDto;
import com.nocaffeine.ssgclone.review.vo.request.ReviewAddRequestVo;
import com.nocaffeine.ssgclone.review.vo.request.ReviewModifyRequestVo;
import com.nocaffeine.ssgclone.review.vo.request.ReviewRemoveRequestVo;
import com.nocaffeine.ssgclone.review.vo.response.ReviewDetailResponseVo;
import com.nocaffeine.ssgclone.review.vo.response.ReviewImageResponseVo;
import com.nocaffeine.ssgclone.review.vo.response.ReviewListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Review", description = "리뷰")
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "리뷰 등록", description = "리뷰 등록")
    @PostMapping
    public CommonResponse<String> reviewAdd(@RequestBody ReviewAddRequestVo reviewAddRequestVo) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        reviewService.addReview(ReviewAddRequestDto.voToDto(reviewAddRequestVo), memberUuid);
        return CommonResponse.success("리뷰 등록 성공");
    }

    @Operation(summary = "리뷰 삭제", description = "리뷰 삭제")
    @DeleteMapping
    public CommonResponse<String> reviewRemove(@RequestBody ReviewRemoveRequestVo reviewRemoveRequestVo) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        reviewService.removeReview(ReviewRemoveRequestDto.voToDto(reviewRemoveRequestVo), memberUuid);
        return CommonResponse.success("리뷰 삭제 성공");
    }

    @Operation(summary = "리뷰 수정", description = "리뷰 수정")
    @PutMapping
    public CommonResponse<String> reviewModify(@RequestBody ReviewModifyRequestVo reviewModifyRequestVo) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        reviewService.modifyReview(ReviewModifyRequestDto.voToDto(reviewModifyRequestVo), memberUuid);
        return CommonResponse.success("리뷰 수정 성공");
    }

    @Operation(summary = "리뷰 상세 조회", description = "리뷰 상세 조회")
    @GetMapping("{reviewId}")
    public CommonResponse<ReviewDetailResponseVo> reviewDetail(@PathVariable("reviewId") Long reviewId) {
        return CommonResponse.success("리뷰 상세 조회 성공",
                ReviewDetailResponseDto.dtoToVo(reviewService.findReviewDetail(reviewId)));
    }


    @Operation(summary = "상품별 리뷰 리스트 조회", description = "상품별 리뷰 리스트 조회")
    @GetMapping("/product/{productId}")
    public CommonResponse<List<ReviewListResponseVo>> reviewList(@PathVariable("productId") Long productId) {
        List<ReviewListResponseVo> reviewListResponseVo = new ArrayList<>();

        for (ReviewListResponseDto reviewListResponseDto : reviewService.findReviewByProduct(productId)) {
            reviewListResponseVo.add(ReviewListResponseDto.dtoToVo(reviewListResponseDto));
        }

        return CommonResponse.success("상품별 리뷰 리스트 조회 성공",reviewListResponseVo);
    }

    @Operation(summary = "리뷰 이미지 조회", description = "리뷰 이미지 조회")
    @GetMapping("/{reviewId}/image")
    public CommonResponse<List<ReviewImageResponseVo>> reviewImageList(@PathVariable("reviewId") Long reviewId) {
        List<ReviewImageResponseVo> reviewImageResponseVo = new ArrayList<>();

        for (ReviewImageResponseDto reviewImageResponseDto  : reviewService.findReviewImage(reviewId)) {
            reviewImageResponseVo.add(ReviewImageResponseDto.dtoToVo(reviewImageResponseDto));
        }

        return CommonResponse.success("리뷰 이미지 조회 성공",reviewImageResponseVo);
    }

    @Operation(summary = "내가 작성한 리뷰 리스트 조회", description = "내가 작성한 리뷰 리스트 조회")
    @GetMapping("/member")
    public CommonResponse<List<ReviewListResponseVo>> reviewFindMember() {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        List<ReviewListResponseVo> reviewListResponseVo = new ArrayList<>();

        for (ReviewListResponseDto reviewListResponseDto : reviewService.findMyReviews(memberUuid)) {
            reviewListResponseVo.add(ReviewListResponseDto.dtoToVo(reviewListResponseDto));
        }

        return CommonResponse.success("내가 작성한 리뷰 리스트 조회 성공",reviewListResponseVo);
    }

    @Operation(summary = "내가 작성할 수 있는 리뷰 리스트 조회", description = "내가 작성할 수 있는 리뷰 리스트 조회")
    @GetMapping("/member/possible")
    public CommonResponse<List<ReviewPossibleWriteResponseDto>> reviewFindMemberPossible() {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());

        return CommonResponse.success("내가 작성할 수 있는 리뷰 리스트 조회 성공",reviewService.findWritableReviews(memberUuid));
    }

}
