package com.nocaffeine.ssgclone.deliveryaddress.infrastructure;

import com.nocaffeine.ssgclone.deliveryaddress.domain.DeliveryAddress;
import com.nocaffeine.ssgclone.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    List<DeliveryAddress> findByMember(Member member);

    Optional<DeliveryAddress> findByMemberAndDefaultCheck(Member member, boolean b);

    Optional<DeliveryAddress> findByIdAndMember(Long deliveryAddressId, Member member);
}
