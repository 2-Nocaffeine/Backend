package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.product.domain.SizeOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeOptionRepository extends JpaRepository<SizeOption, Long> {
}
