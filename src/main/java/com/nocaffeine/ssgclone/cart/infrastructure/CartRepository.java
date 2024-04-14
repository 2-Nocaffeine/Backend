package com.nocaffeine.ssgclone.cart.infrastructure;

import com.nocaffeine.ssgclone.cart.domain.Cart;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{


    Optional<Cart> findByMemberAndOptionSelectedProduct(Member member, OptionSelectedProduct optionSelectedProduct);

    List<Cart> findByMember(Member member);

    List<Cart> findByCheckProductAndMember(boolean b, Member member);
}
