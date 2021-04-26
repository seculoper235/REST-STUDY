package com.example.demo.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/* Request 객체?
 * 지금 현재 애플리케이션에서는 User 엔티티로 값을 받아오게 되어있다.
 * 이게 무슨 소리냐면, 사용자가 User 정보를 입력할 때 소속 팁 id까지 같이 입력해야 한다는 소리다.
 * {
    "name": "테스터1",
    "description": "테스터1입니다.",
    "team": {
        "id":  1,
        "name": "팀1"}
 * }
 * 하지만 사용자가 팀 id가 뭔지 어떻게 알까? id는 DB만 알고 있는 정보이다. 사용자는 알아선 안되고, 알 필요도 없다.
 * 이럴땐 response와 마찬가지로 필요한 객체만 받도록 작성하면 된다.
 * 여기선 id없이 name만 받아오는 형태로 작성해보겠다. 작성 형태는 값을 활용하는 것은 클라이언트이므로, 그 쪽과 잘 상의하여 결정하면 되겠다. */
@Getter
@Setter
@Builder
public class UserRequest {
    private String name;
    private String description;
    private String teamName;

}
