package com.nocaffeine.ssgclone.category.infrastructure;

import com.nocaffeine.ssgclone.category.domain.LargeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeCategoryRepository extends JpaRepository<LargeCategory, Long>{
    LargeCategory findByName(String name);
}
