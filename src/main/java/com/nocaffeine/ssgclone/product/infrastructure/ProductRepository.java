package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);
    // Optional 은 null 을 리턴하지 않고, null 대신에 Optional.empty()를 리턴하는 메소드
    // Optional.empty()를 리턴하게 되면 클라이언트는 null 체크를 하지 않아도 된다.
    // 즉, 클라이언트는 Optional 객체가 비어있는지 확인하는 메소드를 사용하여 null 체크를 할 수 있다.


}
