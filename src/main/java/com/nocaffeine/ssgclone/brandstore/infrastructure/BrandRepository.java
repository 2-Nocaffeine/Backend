package com.nocaffeine.ssgclone.brandstore.infrastructure;

import com.nocaffeine.ssgclone.brandstore.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
