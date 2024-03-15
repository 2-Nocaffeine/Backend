package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.product.domain.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {
}
