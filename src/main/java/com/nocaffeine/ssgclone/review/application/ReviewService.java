package com.nocaffeine.ssgclone.review.application;

import com.nocaffeine.ssgclone.review.dto.request.ReviewAddRequestDto;

public interface ReviewService {

    void addReview(ReviewAddRequestDto reviewAddRequestDto, String memberUuid);
}
