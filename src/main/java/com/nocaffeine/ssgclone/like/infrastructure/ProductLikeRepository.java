package com.nocaffeine.ssgclone.like.infrastructure;

import com.nocaffeine.ssgclone.like.domain.ProductLike;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
    Optional<ProductLike> findByMemberAndProduct(Member member, Product product);
}