package VO;

/* Entity의 단점
 * 그렇지만 언제까지 @JsonIgnore를 붙일 수도 없고, 사실 엔티티에 @JsonIgnore를 붙이는 것은 엔티티를 잘못 사용하고 있는 것이다.
 * 엔티티란 어디까지나 DB상의 테이블과 1:1 매핑되는 객체로써, 문제없이 데이터를 받아올 수 있기만 하면 된다.
 * 즉, 엔티티 자체를 데이터 출력을 위한 용도로 사용되선 안된다는 것이다.
 * 그럼 데이터 출력을 위해선 어떤 객체가 필요하냐 하면, 우선 VO, Value Object라는 것이 필요하다.
 */

import lombok.Builder;

import java.util.Objects;

/* VO란?
 * Value Object로 순수하게 값을 담는 객체이다.
 * Equal과 hash 메소드가 있는 것이 특징인데 이건 부가적인 것이고, 가장 중요한 목적은 Entity의 속성들을 사용이 편리하도록 세팅한 객체라는데 의미가 있다.
 * 엔티티가 데이터를 잘 받아오긴 하지만, 참조 관계에서 객체를 그대로 가져온다.
 * 예를 들어 User 객체에서 우리는 team_id가 필요한데, team 객체 자체를 가져와버리는 것이다.
 * 매번 사용할 때 id만 따로 뺴니기도 힘들고, 필드 자체를 id만 받게 수정하자니 JPA 특성상 불가능하다.
 * 또한 간단한 validation은 괜찮지만, 이도 점점 많아지면 Entity에 너무 많은 부담이 가게 된다.
 *
 * 그래서 데이터를 받아오는 것이 아닌 실 사용에 편리하도록 속성이나 validate 등을 세팅한 객체이며, 별다른 설정이 없다면 DB의 테이블과 동일하게 세팅한다.
 */
@Builder
public class TeamVo {
    private final int id;
    private final String name;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        TeamVo team = (TeamVo) obj;
        return Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
