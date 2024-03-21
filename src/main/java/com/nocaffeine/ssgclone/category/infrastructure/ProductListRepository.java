package com.nocaffeine.ssgclone.category.infrastructure;

import com.nocaffeine.ssgclone.category.domain.MediumCategory;
import com.nocaffeine.ssgclone.category.domain.ProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Long>{
    List<ProductList> findByLargeCategory_Id(Long largeId);

    Optional<ProductList> findByProduct_Id(Long productId);
}
