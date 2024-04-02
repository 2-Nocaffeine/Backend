package com.nocaffeine.ssgclone.brandstore.infrastructure;

import com.nocaffeine.ssgclone.brandstore.domain.BrandList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandListRepository extends JpaRepository<BrandList, Long>{
    Optional<BrandList> findByProductId(Long productId);

    @Query("SELECT b.brand.id FROM BrandList b WHERE b.product.id = :productId")
    String findBrandNameByProductId(Long productId);
}
