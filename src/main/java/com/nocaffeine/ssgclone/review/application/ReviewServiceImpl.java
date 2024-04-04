package com.nocaffeine.ssgclone.review.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.order.infrastructure.OrderProductRepository;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import com.nocaffeine.ssgclone.product.domain.Image;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.infrastructure.ImageRepository;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import com.nocaffeine.ssgclone.review.domain.Review;
import com.nocaffeine.ssgclone.review.domain.ReviewImage;
import com.nocaffeine.ssgclone.review.dto.request.ReviewAddRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewModifyRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewRemoveRequestDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewListResponseDto;
import com.nocaffeine.ssgclone.review.infrastructure.ReviewImageRepository;
import com.nocaffeine.ssgclone.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ImageRepository imageRepository;


    /**
     * 리뷰 등록
     */
    @Override
    @Transactional
    public void addReview(ReviewAddRequestDto reviewAddRequestDto, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        Product product = productRepository.findById(reviewAddRequestDto.getProductId())
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

//        Orders order = orderRepository.findById(reviewAddRequestDto.getOrderId())
//                .orElseThrow(() -> new BaseException(NO_EXIST_ORDER));

        Review review = reviewRepository.save(Review.builder()
                .product(product)
                .memberUuid(member.getUuid())
                .content(reviewAddRequestDto.getContent())
                .rate(reviewAddRequestDto.getRate())
//                .order(order)
                .build());

        // 이미지 저장
        for (String imageUrl : reviewAddRequestDto.getImageUrl()) {
            Image image = imageRepository.save(Image.builder()
                    .url(imageUrl)
                    .build());

            // 리뷰 이미지
            reviewImageRepository.save(ReviewImage.builder()
                    .review(review)
                    .image(image)
                    .build());
        }
    }

    /**
     * 리뷰 삭제
     */
    @Override
    @Transactional
    public void removeReview(ReviewRemoveRequestDto reviewRemoveRequestDto, String memberUuid) {
        Review review = reviewRepository.findById(reviewRemoveRequestDto.getReviewId())
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        List<ReviewImage> reviewImage = reviewImageRepository.findByReview(review);
        reviewImageRepository.deleteAll(reviewImage);

        reviewRepository.delete(review);
    }




    /*    *//**
     * 작성 가능한 리뷰 조회
     *//*
    @Override
    public void findWritableReviews(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        List<Orders> order = orderRepository.findAllByUuid(member.getUuid());

        // 주문리스트 조회
        for (Orders orders : order) {
            // 각 주문별 상품 조회
            List<OrderProduct> orderProduct = orderProductRepository.findAllByOrder(orders);

            for (OrderProduct orderProducts : orderProduct) {
                reviewRepository.findAllByOrder(orders);
                if (reviewRepository.findAllByOrder(orders).isEmpty()) {
                }
            }
        }
    }*/

    /**
     * 리뷰 수정
     */
    @Override
    @Transactional
    public void modifyReview(ReviewModifyRequestDto reviewModifyRequestDto, String memberUuid) {
        Review review = reviewRepository.findById(reviewModifyRequestDto.getReviewId())
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        Review newReview = reviewRepository.save(Review.builder()
                .id(review.getId())
                .product(review.getProduct())
                .memberUuid(review.getMemberUuid())
                .content(reviewModifyRequestDto.getContent())
                .rate(reviewModifyRequestDto.getRate())
                .order(review.getOrder())
                .build()
        );

        // 기존 사진 삭제후 새로운 사진 추가
        List<ReviewImage> reviewImage = reviewImageRepository.findByReview(review);
        reviewImageRepository.deleteAll(reviewImage);
        reviewRepository.delete(review);

        for (String imageUrl : reviewModifyRequestDto.getImageUrl()) {
            Image image = imageRepository.save(Image.builder()
                    .url(imageUrl)
                    .build());

            reviewImageRepository.save(ReviewImage.builder()
                    .review(newReview)
                    .image(image)
                    .build());
        }
    }

    @Override
    public List<ReviewListResponseDto> findReviewByProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        List<Review> review = reviewRepository.findByProduct(product);

        List<ReviewListResponseDto> reviewListResponseDto = new ArrayList<>();
        for (Review reviews : review) {
            Member member = memberRepository.findByUuid(reviews.getMemberUuid())
                    .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

            reviewListResponseDto.add(ReviewListResponseDto.builder()
                    .reviewId(reviews.getId())
                    .memberName(maskEmail(member.getEmail()))
                    .content(reviews.getContent())
                    .rate(reviews.getRate())
                    .createdAt(reviews.getCreatedAt().toString())
                    .build());
        }

        return reviewListResponseDto;
    }

    private String maskEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex >= 0) {
            String prefix = email.substring(0, Math.min(3, atIndex));
            String maskedPrefix = prefix + "*******";
            return maskedPrefix;
        } else {
            return email;
        }

    }
}
