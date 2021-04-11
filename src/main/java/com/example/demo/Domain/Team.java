package com.example.demo.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/* team의 경우, 딱히 레이지 로딩을 사용하지 않기 때뮨에 JsonIgnoreProperties가 필요없다 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "team")
public class Team {
    @Id @GeneratedValue
    private int id;
    private String name;

    /* 순환 참조 문제
     * 양방향 매핑은 사실 맺어주지 않는 것이 좋다. 순환 참조가 일어나고, 관계가 복잡해지기 때문이다.
     * 하지만 그럼에도 불구하고 꼭 필요할 때가 있다.
     * (각 팁 별로 직원이 총 몇명 있는지 알아야 할 때 등)
     * 이럴 때 그냥 사용한다면, team에서 user를 조회하고, user에서 다시 team을 조회하는 순환 참조가 일어나게 된다.
     * 이를 방지하기 위해선 연관관계 둘 중 하나를 무시하거나, 레이지 로딩으로 조정해주는 방법이 있다.
     * 여기선 team 별로 user 들의 정보가 조회되길 원하므로, user 쪽의 team 필드에 레이지 로딩을 적용시켜준다.
     */
    @OneToMany(mappedBy = "team")
    private List<User> users = new ArrayList<>();
}
