package com.nocaffeine.ssgclone.common.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {

    @PersistenceContext
    private EntityManager entityManager;
    // EntityManager 라는 JPA 에서 제공하는 인터페이스를 사용하여 JPAQueryFactory 를 생성

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
    // JPAQueryFactory 를 빈으로 등록
}
