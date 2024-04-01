package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.domain.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {

    List<ViewHistory> findByMemberOrderByCreatedAtDesc(Member member);

    void deleteByMemberAndProduct(Member member, Product product);

    Optional<ViewHistory> findByMemberAndProduct(Member member, Product product);
}