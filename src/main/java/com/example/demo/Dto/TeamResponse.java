package com.example.demo.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/* DTO란?
 * Data Transfer Object의 약자로, 계층 간 데이터 교환을 위해 쓰이는 객체이다.
 * VO는 어디까지나 애플리케이션 내에서 유용하게 쓰기 위해 값을 담는 객체이다.
 * 그러나 출력하기 위한 객체는 다를 수도 있다. User 하나, Team 하나만 출력하면 좋겠지만, 실상은 Team 객체에 User 내용이 섞일 수도 있고,
 * 사용자 프로필 이미지인 UserImage 객체가 있다면 그것도 포함될 수 있고, 출력 데이터는 클라이언트 마음이다.
 * 하지만 VO는 불변성인데다가 입맛대로 바꿀 수 있는 객체가 아니다. Presentation 계층에서 유연하게 사용할 수 있는 데이터 객체가 필요하다.
 * 서버단과 클라이언트 단 사이의 데이터 교환을 위한, 즉 Presentation 계층을 위한 데이터가 필요하다. 그것이 바로 DTO 이다!
 *
 * 해당 객체는 클라이언트를 위한 객체이므로, 언마든지 생성되고 바뀔 수 있는 객체이다. 때문에 getter/setter, 생성자 등이 필요하다.
 * VO와 달리 출력의 형태를 변환해주기 위해 데이터를 잠시 담아두는 객체이므로, 별도의 Repository는 필요 없다.
 * 여기서는 count를 담은 Response 객체를 정의했다.
 */
@Getter @Setter
@Builder
public class TeamResponse {
    private int id;
    private String name;
    private int count;
}
