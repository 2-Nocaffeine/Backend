package com.nocaffeine.ssgclone.review.domain;

import com.nocaffeine.ssgclone.product.domain.Image;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    @Builder
    public ReviewImage(Long id, Image image, Review review) {
        this.id = id;
        this.image = image;
        this.review = review;
    }
}
