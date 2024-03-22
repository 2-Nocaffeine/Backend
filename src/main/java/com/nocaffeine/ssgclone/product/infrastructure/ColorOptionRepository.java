package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.product.domain.ColorOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorOptionRepository extends JpaRepository<ColorOption, Long> {
}
