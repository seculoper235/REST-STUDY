package com.example.demo.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;



/* JsonIgnore 설정 이유?
 * getOne()은 객체를 레이지 로딩한다. 이때 사용되는 것이 hibernateLazyInitializer 인데,
 * 보통 쓸 일이 없기에 보이진 않지만 우리가 조회한 엔티티의 속성에 포함되어 있다. MVC를 사용할 땐, get/set 을 사용하므로 해당 문제가 없을 것이다.
 * 하지만 문제는 REST API 에서 일어난다.
 *
 * JSON 은 별다른 설정이 없다면, 조회한 데이터(엔티티)의 모든 속성을 싹 다 직렬화하여 표현한다.
 * 이떄 직렬화를 위해 사용하는 것이 Serializer인데, 문자열은 StringSerialzer, 숫자는 NumberSerializer 등을 사용하여 직렬화한다.
 * 하지만 문제는 또다른 속성인 hibernateLazyInitializer 이다.
 * 이것은 처리할 수 있는 Serializer가 당연히 없다. 이유는 애초에 자료형이 아니기 떄문!
 * 따라서 처리할 수 없다고 판단하고 UnknownSerializer 를 부여하고, "처리할 수 없는 Serializer가 없습니다"라는 오류를 띄운다.
 *
 * 그러므로 JsonIgnoreProperties()로 "이 설정은 읽지마!"라고 명시를 해주면, hibernate 속성은 건너뛰고 정상 출력을 하게 된다.
 */

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    /* JSON 출력은 별개의 문제
     * 그러나 레이지 로딩을 해도 런타임 에러가 나지 않는다 뿐이지, 여전히 순환 참조가 일어나서 데이터가 무한 반복되는 것을 볼 수 있다.
     * 이건 JPA 설정을 잘못한 것이 아니라, 순전히 JSON의 문제이다.
     * JSON은 앞서 말했다시피, 모든 속성을 출력하려고 한다. 레이지 로딩이고 뭐고 없다.
     * 따라서 굳이 출력해주고 싶다면 역시 @JsonIgnore를 붙이면 된다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
