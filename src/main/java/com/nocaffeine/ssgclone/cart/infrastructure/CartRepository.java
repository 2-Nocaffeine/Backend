package com.nocaffeine.ssgclone.cart.infrastructure;

import com.nocaffeine.ssgclone.cart.domain.Cart;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{


    Optional<Cart> findByMemberAndProductOption(Member member, ProductOption productOption);


    List<Cart> findByMember(Member member);
}
