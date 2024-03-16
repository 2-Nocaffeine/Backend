package com.nocaffeine.ssgclone.cart.infrastructure;

import com.nocaffeine.ssgclone.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
