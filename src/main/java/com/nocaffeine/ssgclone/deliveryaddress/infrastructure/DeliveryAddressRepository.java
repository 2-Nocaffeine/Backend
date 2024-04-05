package com.nocaffeine.ssgclone.deliveryaddress.infrastructure;

import com.nocaffeine.ssgclone.deliveryaddress.domain.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
}
