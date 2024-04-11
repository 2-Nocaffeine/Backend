package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.product.domain.AddOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddOptionRepository extends JpaRepository<AddOption, Long> {
}
