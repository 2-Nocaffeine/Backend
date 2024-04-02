package com.nocaffeine.ssgclone.review.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import com.nocaffeine.ssgclone.review.domain.Review;
import com.nocaffeine.ssgclone.review.dto.request.ReviewAddRequestDto;
import com.nocaffeine.ssgclone.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_EXIST_MEMBERS;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_PRODUCT;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void addReview(ReviewAddRequestDto reviewAddRequestDto, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));


        Product product = productRepository.findById(reviewAddRequestDto.getProductId())
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        Review review = Review.builder()
                .product(product)
                .memberUuid(member.getUuid())
                .content(reviewAddRequestDto.getContent())
                .rate(reviewAddRequestDto.getRate())
                .build();


        reviewRepository.save(review);

    }

}
