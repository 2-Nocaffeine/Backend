package com.nocaffeine.ssgclone.review.application;

import com.nocaffeine.ssgclone.review.dto.request.ReviewAddRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewModifyRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewRemoveRequestDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewDetailResponseDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewImageResponseDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewListResponseDto;

import java.util.List;

public interface ReviewService {

    void addReview(ReviewAddRequestDto reviewAddRequestDto, String memberUuid);

    void removeReview(ReviewRemoveRequestDto reviewRemoveRequestDto, String memberUuid);

//    void findWritableReviews(String memberUuid);

    void modifyReview(ReviewModifyRequestDto reviewModifyRequestDto, String memberUuid);

    List<ReviewListResponseDto> findReviewByProduct(Long productId);

    ReviewDetailResponseDto findReviewDetail(Long reviewId);

    List<ReviewImageResponseDto> findReviewImage(Long reviewId);
}
