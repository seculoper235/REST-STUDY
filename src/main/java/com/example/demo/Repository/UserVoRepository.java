package com.example.demo.Repository;

import VO.UserVo;
import com.example.demo.Domain.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/* JPQL과 QueryDSL?
 * VO 객체에 값을 담을 차례인데, 해당 객체는 DB와 연동하는 객체가 아니다. 따라서 엔티티 객체, 즉 User로 부터 값을 받아와야 한다.
 * 어떻게 값을 받아올 수 있을까? 우선 생각나는 방법은 User 객체를 통째로 가져와서, 필요한 값만 뽑은 다음, 생성자로 UserVo에 세팅해주면 된다.
 * 하지만 이 방법은 번거로울 뿐더러, 객체 2개의 내용이 섞이는 VO라면 더는 되돌릴 수 없는 코드가 될 것이다.
 * 이럴땐 UserVo의 속성에 맞는 필드만 조회하여, 깔끔하게 바로 초기화 할 수 있게 해야 한다. 즉, 커스텀 쿼리문이 필요하다.
 * JPQL과 QueryDSL은 이러한 커스텀 쿼리를 사용하기 편하게 해준다.
 * JPQL은 설정과 사용이 간단하므로, 여기서는 QueryDSL에 대해 알아보자. */

/* QueryDSL이란?
 * 아무리 오리지날 SQL 작성에서 벗어나 프로그래밍을 한다고 하더라도, native하게 쿼리를 날릴 필요가 분명히 있다. 아니 대부분이다.
 * 출력하고 싶은 데이터가 엔티티와 조금만 달라져도 우리는 JPARepository를 사용할 수 없다. 허나 그렇다고 해서 쿼리를 하드코딩으로 우겨넣을 순 없는 일이다.
 * 어떻게 하면 좀더 native 쿼리문을 코드로 간편하게 작성할 수 있을까?
 * 우리는 자유롭고 직관적으로 값을 설정하고 초기화 할 수 있는 디자인 패턴을 알고있다. 바로 "빌더 패턴"이다.
 * QueryDSL은 빌더 패턴을 사용하여, 쿼리문을 쉽게 작성할 수 있게 한다. */
@Repository
public class UserVoRepository {
    /* 2. 사용하기
     * 그리고 여기서 불러와 사용하면 된다. */
    @Autowired
    public JPAQuery<?> query;

    /* 프로젝션?
     * 사용 방법은 직관적이니 넘어가고, 프로젝션에 대해 알아보자.
     * 프로젝션이란 별다른 건 없고, 원하는 필드만 뽑아내어 담겠다는 것이다.
     * 튜플로 담는 방법과 객체에 담는 방법이 있는데, UserVo 객체에 담을 것이므로 객체 방법을 살펴보자.
     * 객체 방법도 3가지로 나뉜다. setter, field, 생성자로 담을 수 있다.(각각 bean(), fields(), constructor()를 사용함)
     * 하지만 VO 특성상 setter는 있을 수 없고,
     * fields는 public이던 private건 상관없이 필드에 직접 접근하는 방식이다. 뭐든간에 강제로 설정하는 방식은 좋지 않다고 생각하여 건너 뛰었고,
     * 그중 생성자 방식이 가장 안전하고, 마음에 들어서 채택하였다. */
    public UserVo findUserVO(int id) {
        QUser user = QUser.user;
        // 사용 방법은 첫번째 인자로 값을 담을 VO 클래스를 넣어주고, 나머지는 생성자 초기화하듯 user에서 값을 뽑아 넣어주면 된다.
        UserVo userVo = query
                .select(Projections.constructor(UserVo.class, user.id, user.name, user.description, user.team.id))
                .from(user)
                .where(user.id.eq(id))
                .fetchOne();

        return userVo;
    };
}
