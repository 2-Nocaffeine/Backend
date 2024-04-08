package com.nocaffeine.ssgclone.specialprice.infrastructure;

import com.nocaffeine.ssgclone.specialprice.domain.SpecialPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialPriceRepository extends JpaRepository<SpecialPrice, Long> {
}
