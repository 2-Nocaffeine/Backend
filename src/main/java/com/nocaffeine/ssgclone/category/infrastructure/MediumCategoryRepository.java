package com.nocaffeine.ssgclone.category.infrastructure;

import com.nocaffeine.ssgclone.category.domain.MediumCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediumCategoryRepository extends JpaRepository<MediumCategory, Long> {

    List<MediumCategory> findByLargeCategoryId(Long largeCategoryId);
}
