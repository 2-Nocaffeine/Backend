package com.nocaffeine.ssgclone.order.infrastructure;

import com.nocaffeine.ssgclone.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o.id FROM Orders o WHERE o.uuid = :memberUuid")
    List<Long> findByUuid(String memberUuid);

}
