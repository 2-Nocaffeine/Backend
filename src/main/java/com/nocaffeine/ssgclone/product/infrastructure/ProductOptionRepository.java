package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
}
