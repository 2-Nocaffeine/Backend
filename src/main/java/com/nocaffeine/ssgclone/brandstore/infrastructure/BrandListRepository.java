package com.nocaffeine.ssgclone.brandstore.infrastructure;

import com.nocaffeine.ssgclone.brandstore.domain.BrandList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandListRepository extends JpaRepository<BrandList, Long>{
}
