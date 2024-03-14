package com.nocaffeine.ssgclone.product.repository;

import com.nocaffeine.ssgclone.product.domain.Total;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalRepository extends JpaRepository<Total, Long> {
}
