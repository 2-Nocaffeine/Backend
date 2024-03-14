package com.nocaffeine.ssgclone.member.repository;

import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

    Optional<Member> findByUUID(UUID uuid);

}
