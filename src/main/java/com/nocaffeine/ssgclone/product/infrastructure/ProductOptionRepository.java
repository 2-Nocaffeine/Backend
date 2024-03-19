package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    List<ProductOption> findByProductId(Long id);
//    Optional<ProductOption> findById(Long id);

    @Query("SELECT po FROM ProductOption po JOIN FETCH po.product JOIN FETCH po.sizeOption JOIN FETCH po.colorOption JOIN FETCH po.addOption WHERE po.id = :id")
    Optional<ProductOption> findById(@Param("id") Long id);
}