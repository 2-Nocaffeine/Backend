package com.nocaffeine.ssgclone.like.infrastructure;

import com.nocaffeine.ssgclone.brandstore.domain.Brand;
import com.nocaffeine.ssgclone.like.domain.BrandLike;
import com.nocaffeine.ssgclone.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandLikeRepository extends JpaRepository<BrandLike, Long> {
    Optional<BrandLike> findByMemberAndBrand(Member member, Brand brand);

    List<BrandLike> findByMember(Member member);
}
