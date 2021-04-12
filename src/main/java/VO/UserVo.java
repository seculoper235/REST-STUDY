package VO;

import java.util.Objects;

/* 기본적인 구성은 다음과 같고, 어떻게 활용하느냐에 따라 validation과 몇 가지 메소드가 구현될 수 있다.
* 중요한 점은 불변성을 고려하여 신중히 작성해야 한다. 쓸데없는 메소드는 작성을 금한다.
* 또한 객체를 받아와야 하므로, 이것도 별도의 Repository가 필요하다. */
public class UserVo {
    private final int id;
    private final String name;
    private final String description;

    // 이것이 바로 Entity와 다른 점이다. 객체가 아닌 테이블과 동일하게 team_id 값을 사용한다.
    private int teamId;

    /* VO 특징 1번째, 불변성이다.
     * VO 객체는 실사용 되는 만큼, 불변성이 보장되어야 한다. 즉, setter를 사용해서 입맛대로 여기저기서 바꿀 수 없다는 것이다.
     * 이를 위해 생성자를 만들어 객체를 사용하려면 반드시 생성자를 사용하게끔 한다.
     * (빌더 패턴이 훨씬 더 간편하지만, 설명용으로 작성)
     */
    public UserVo(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /* equals()는 동등성을 의미한다.
     * 보통 ==를 쓰게 되면, 이것은 속성뿐만 아니라 객체의 주소값까지 같다는 것이다. 즉, 완전히 동일한 객체란 뜻이 된다.
     * 하지만 보통 객체를 쓸 땐, 다른 객체라도 속성값이 같으면 충분한 경우가 많다.
     * 그런 경우를 재정의 하는 메소드이다.
     */
    @Override
    public boolean equals(Object obj) {
        // ==이면 완전 같다는 것이므로 true
        if(this == obj) return true;
        // null 체크 혹은, 클래스 정보(속성들, 근본 클래스 등)이 다르면 false
        if(obj == null || getClass() != obj.getClass()) return false;
        // 모두 통과한다면 최소한 동일한 클래스임이 보장되므로 형변환 후 Objects.equals에 넘김
        UserVo user = (UserVo) obj;
        return Objects.equals(id, user.id);
    }

    /* hashCode()는 객체 자체의 식별자, 즉 동일성을 의미한다.
     * 이를 사용하면 말 그대로 객체 자체를 따로 식별할 수 있다.
     * 하지만 가벼운 작업은 아니므로, 무분별한 사용은 금해야 한다.
     * 이 경우 User의 식별자로 id가 필요하지만, 굳이 필요하지 않은 경우도 있다. 그럴 땐 정의할 필요가 없다.
     * 부가적이란 것이 이런 뜻이다. 무조건적으로 정의하지 말자!
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
