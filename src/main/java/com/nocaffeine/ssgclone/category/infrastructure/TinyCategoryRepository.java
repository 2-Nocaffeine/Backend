package com.nocaffeine.ssgclone.category.infrastructure;

import com.nocaffeine.ssgclone.category.domain.TinyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TinyCategoryRepository extends JpaRepository<TinyCategory, Long> {

    List<TinyCategory> findBySmallCategoryId(Long id);
}
