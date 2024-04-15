package com.nocaffeine.ssgclone.review.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.order.domain.OrderProduct;
import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.infrastructure.OrderProductRepository;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import com.nocaffeine.ssgclone.product.domain.Image;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.domain.Total;
import com.nocaffeine.ssgclone.product.infrastructure.ImageRepository;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import com.nocaffeine.ssgclone.product.infrastructure.TotalRepository;
import com.nocaffeine.ssgclone.review.domain.Review;
import com.nocaffeine.ssgclone.review.domain.ReviewImage;
import com.nocaffeine.ssgclone.review.dto.request.ReviewAddRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewModifyRequestDto;
import com.nocaffeine.ssgclone.review.dto.request.ReviewRemoveRequestDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewDetailResponseDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewImageResponseDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewListResponseDto;
import com.nocaffeine.ssgclone.review.dto.response.ReviewPossibleWriteResponseDto;
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
    private final TotalRepository totalRepository;


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

        Orders order = orderRepository.findById(reviewAddRequestDto.getOrderId())
                .orElseThrow(() -> new BaseException(NO_EXIST_ORDER));

        Review review = reviewRepository.save(Review.builder()
                .product(product)
                .memberUuid(member.getUuid())
                .content(reviewAddRequestDto.getContent())
                .rate(reviewAddRequestDto.getRate())
                .order(order)
                .build());

        addReviewImage(reviewAddRequestDto.getImageUrl(), review);

        updateTotal(reviewAddRequestDto, product);

    }

    // 리뷰 이미지 저장
    private void addReviewImage(List<String> imageUrls, Review review) {
        for (String imageUrl : imageUrls) {
            Image image = imageRepository.save(Image.builder()
                    .url(imageUrl)
                    .build());

            reviewImageRepository.save(ReviewImage.builder()
                    .review(review)
                    .image(image)
                    .build());
        }
    }

    // 리뷰 등록 후 상품 평점 업데이트
    private void updateTotal(ReviewAddRequestDto reviewAddRequestDto, Product product) {
        Total total = totalRepository.findByProduct(product)
                .orElseGet(() -> Total.builder()
                        .product(product)
                        .sales(0)
                        .rateAverage((double) reviewAddRequestDto.getRate())
                        .reviewCount(0)
                        .build());

        double rateAverage = (total.getRateAverage() * total.getReviewCount() + reviewAddRequestDto.getRate()) / (total.getReviewCount() + 1);

        totalRepository.save(Total.builder()
                .id(total.getId())
                .product(total.getProduct())
                .sales(total.getSales())
                .rateAverage(rateAverage)
                .reviewCount(total.getReviewCount() + 1)
                .build());
    }


    /**
     * 리뷰 삭제
     */
    @Override
    @Transactional
    public void removeReview(ReviewRemoveRequestDto reviewRemoveRequestDto, String memberUuid) {
        Review review = reviewRepository.findById(reviewRemoveRequestDto.getReviewId())
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        recoverTotal(review);

        List<ReviewImage> reviewImage = reviewImageRepository.findByReview(review);
        reviewImageRepository.deleteAll(reviewImage);

        reviewRepository.delete(review);


    }

    // 평점 되돌리기
    private void recoverTotal(Review review) {
        Product product = review.getProduct();

        Total total = totalRepository.findByProduct(product)
                .orElseThrow(() -> new BaseException(NO_EXIST_TOTAL));

        double rateAverage = (total.getRateAverage() * total.getReviewCount() - review.getRate()) / (total.getReviewCount() - 1);

        totalRepository.save(Total.builder()
                .id(total.getId())
                .product(total.getProduct())
                .sales(total.getSales())
                .rateAverage(rateAverage)
                .reviewCount(total.getReviewCount() - 1)
                .build());
    }

    /**
     * 리뷰 수정
     */
    @Override
    @Transactional
    public void modifyReview(ReviewModifyRequestDto reviewModifyRequestDto, String memberUuid) {
        Review review = reviewRepository.findById(reviewModifyRequestDto.getReviewId())
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        int previousRate = review.getRate();

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

        addReviewImage(reviewModifyRequestDto.getImageUrl(), newReview);

        updateTotal(reviewModifyRequestDto, review, previousRate);

    }

    // 리뷰 수정 후 평점 업데이트
    private void updateTotal(ReviewModifyRequestDto reviewModifyRequestDto, Review review, int previousRate) {
        // 평점 업데이트
        Product product = review.getProduct();

        Total total = totalRepository.findByProduct(product)
                .orElseThrow(() -> new BaseException(NO_EXIST_TOTAL));

        double rateAverage = (total.getRateAverage() * total.getReviewCount() - previousRate + reviewModifyRequestDto.getRate()) / total.getReviewCount();

        totalRepository.save(Total.builder()
                .id(total.getId())
                .product(total.getProduct())
                .sales(total.getSales())
                .rateAverage(rateAverage)
                .reviewCount(total.getReviewCount())
                .build());
    }


    /**
     * 작성 가능한 리뷰 조회
     */
    @Override
    public List<ReviewPossibleWriteResponseDto> findWritableReviews(String memberUuid) {
        List<Orders> orders = orderRepository.findByUuid(memberUuid);

        List<ReviewPossibleWriteResponseDto> writableReviews = new ArrayList<>();


        for (Orders order : orders) {
            List<OrderProduct> orderProducts = orderProductRepository.findAllByOrder(order);

            for (OrderProduct orderProduct : orderProducts) {
                // 해당 상품에 대한 리뷰가 이미 작성되었는지 확인
                List<Review> reviews = reviewRepository.findAllByOrderAndProductId(order, orderProduct.getProductId());

                // 리뷰가 작성되지 않은 경우 작성 가능한 리뷰 목록에 추가
                if (reviews.isEmpty()) {
                    writableReviews.add(ReviewPossibleWriteResponseDto.builder()
                            .productId(orderProduct.getProductId())
                            .productName(orderProduct.getProductName())
                            .build());
                }
            }
        }

        return writableReviews;
    }


    /**
     * 상품별 리뷰 조회
     */
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

    // 이메일 마스킹
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


    /**
     * 리뷰 상세 조회
     */
    @Override
    public ReviewDetailResponseDto findReviewDetail(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        Member member = memberRepository.findByUuid(review.getMemberUuid())
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        return ReviewDetailResponseDto.builder()
                .reviewId(review.getId())
                .memberName(maskEmail(member.getEmail()))
                .content(review.getContent())
                .rate(review.getRate())
                .createdAt(review.getCreatedAt().toString())
                .build();
    }

    /**
     * 리뷰 이미지 조회
     */
    @Override
    public List<ReviewImageResponseDto> findReviewImage(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        List<ReviewImage> reviewImages = reviewImageRepository.findByReview(review);

        List<ReviewImageResponseDto> reviewImageResponseDtoList = new ArrayList<>();

        for (ReviewImage reviewImage : reviewImages) {
            Image image = reviewImage.getImage();
            reviewImageResponseDtoList.add(ReviewImageResponseDto.builder()
                    .imageUrl(image.getUrl())
                    .build());
        }

        return reviewImageResponseDtoList;

    }

    /**
     * 내가 작성한 리뷰 조회
     */
    @Override
    public List<ReviewListResponseDto> findMyReviews(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        List<Review> reviews = reviewRepository.findByMemberUuid(member.getUuid());

        List<ReviewListResponseDto> reviewListResponseDto = new ArrayList<>();

        for (Review review : reviews) {
            reviewListResponseDto.add(ReviewListResponseDto.builder()
                    .reviewId(review.getId())
                    .content(review.getContent())
                    .rate(review.getRate())
                    .createdAt(review.getCreatedAt().toString())
                    .build());
        }
        return reviewListResponseDto;
    }
}
