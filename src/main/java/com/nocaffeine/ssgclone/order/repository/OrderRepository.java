package com.nocaffeine.ssgclone.order.repository;

import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.order.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Orders, Long> {
//member로 들어옴 모든 정보가
}
