package com.nocaffeine.ssgclone.brandstore.infrastructure;

import com.nocaffeine.ssgclone.brandstore.domain.BrandList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandListRepository extends JpaRepository<BrandList, Long>{
    Optional<BrandList> findByProductId(Long productId);

    @Query("SELECT b.brand.name FROM BrandList b WHERE b.product.id = :productId")
    String findBrandNameByProductId(@Param("productId") Long productId);

    List<BrandList> findByBrandId(Long brandId);

    Page<BrandList> findByBrandId(Long brandId, Pageable page);
}
