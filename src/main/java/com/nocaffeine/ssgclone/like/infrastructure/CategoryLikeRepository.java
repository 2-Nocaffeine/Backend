package com.nocaffeine.ssgclone.like.infrastructure;

import com.nocaffeine.ssgclone.like.domain.CategoryLike;
import com.nocaffeine.ssgclone.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryLikeRepository extends JpaRepository<CategoryLike, Long>{
    Optional<CategoryLike> findByMemberAndMediumCategoryAndSmallCategoryAndTinyCategory(Member member, Long mediumCategoryId, Long smallCategoryId, Long tinyCategoryId);

    List<CategoryLike> findByMember(Member member);

    Optional<CategoryLike> findByIdAndMember(Long categoryLikeId, Member member);
}
