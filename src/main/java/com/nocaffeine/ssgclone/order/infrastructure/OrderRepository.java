package com.nocaffeine.ssgclone.order.infrastructure;

import com.nocaffeine.ssgclone.order.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUuid(String memberUuid);

    Optional<Orders> findByOrderNumber(Long orderNumber);

    Optional<Orders> findByOrderPhoneAndOrderNumber(String orderPhone, String orderNumber);
}
