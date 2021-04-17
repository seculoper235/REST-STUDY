package com.example.demo.Config;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/* 1. JPAQuery 얻어내기
 * QueryDSL은 자동 설정되는 것이 아니기 때문에, 몇가지 설정이 필요하다.
 * 우선 커스텀 쿼리를 날릴 수 있게하는 객체가 필요한데, 그 객체가 JPAQuery이다.
 * 이름에서 이미 다 알려주고 있듯이, JPA로 커스텀 쿼리를 날리게 해주는 것이다.
 * JPA를 사용하는 것이므로, 필수 객체인 엔티티 매니저를 사용하여 Bean 객체를 얻어낸다. */
@Configuration
public class QueryDslConfig {
    @PersistenceContext
    EntityManager em;

    @Bean
    public JPAQuery<?> jpaQueryFactory() {
        return new JPAQuery<>(em);
    }
}
