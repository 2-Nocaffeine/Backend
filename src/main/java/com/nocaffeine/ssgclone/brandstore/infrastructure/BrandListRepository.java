package com.nocaffeine.ssgclone.brandstore.infrastructure;

import com.nocaffeine.ssgclone.brandstore.domain.BrandList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLType;

@Repository
public interface BrandListRepository extends JpaRepository<BrandList, Long>{

    BrandList findByProductId(Long productId);

}
