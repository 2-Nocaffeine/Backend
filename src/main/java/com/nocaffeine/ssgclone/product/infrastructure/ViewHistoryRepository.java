package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.product.domain.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {

    @Query("SELECT vh FROM ViewHistory vh WHERE vh.member.uuid = :memberUuid ORDER BY vh.createdAt DESC")
    List<ViewHistory> findByMemberUuid(@Param("memberUuid") String memberUuid);
}