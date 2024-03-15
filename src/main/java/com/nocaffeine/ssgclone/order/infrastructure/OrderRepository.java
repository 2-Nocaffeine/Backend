package com.nocaffeine.ssgclone.order.infrastructure;

import com.nocaffeine.ssgclone.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Orders, Long> {
//member로 들어옴 모든 정보가
}
