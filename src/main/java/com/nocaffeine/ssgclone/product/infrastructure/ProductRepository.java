package com.nocaffeine.ssgclone.product.infrastructure;

import com.nocaffeine.ssgclone.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    Optional<Product> findById(Long id);
    // Optional 은 null 을 리턴하지 않고, null 대신에 Optional.empty()를 리턴하는 메소드
    // Optional.empty()를 리턴하게 되면 클라이언트는 null 체크를 하지 않아도 된다.
    // 즉, 클라이언트는 Optional 객체가 비어있는지 확인하는 메소드를 사용하여 null 체크를 할 수 있다.

    // 제품을 찾는 메소드
    List<Product> findByNameContaining(String searchKeyword);

    Page<Product> findByNameContaining(String searchKeyword, Pageable page);

    // 제품 리스트를 10개씩 페이징을 해서 가져오는 메소드
    Page<Product> findAll(Pageable page);

    // 전체 상품 리스트 조회
    List<Product> findAll();

}
