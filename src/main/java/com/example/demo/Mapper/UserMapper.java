package com.example.demo.Mapper;

import com.example.demo.Domain.User;
import com.example.demo.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/* MapStruct란?
 * 오브젝트 매핑을 간편하게 하기위한 라이브러리이다.
 * 수동으로 매핑하는 것보다 간편하게 매핑할 수 있는데, ModelMapper 와는 달리, Reflection 같은 비싼 기술을 기반으로 동작하지 않고,
 * getter/setter 등 간단한 코드만을 사용한다.
 * 여기서는 MapStruct에 대해 살펴볼 것이다. ModelMapper가 작업 비용이 비싸기도하고 이슈도 많이 나오긴 하지만 여전히 많이 사용되고 있으므로
 * 때에 맞게 잘 선택해서 사용하면 될 것 같다.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    /* 1. 인터페이스 만들기
     * 우선 스프링은 해당 맵퍼 인터페이스를 구현하여 필요한 매핑 클래스를 만들어낸다
     * 따라서 해당 인터페이스를 사용할 수 있도록 객체화 할 변수가 필요하다.
     */
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    /* 2. 맵퍼 작성하기
     * 그 다음은 간단하다. 다음처럼 구현하고 싶은 메소드를 작성해주면 된다.
     * 만약 DTO와 Entity가 동일한 변수와 변수명을 가진다면 상관없지만,
     * 변수가 다르다거나, 하나가 무시된다거나 하면 @Mapping 어노테이션으로 해결할 수 있다.
     * Mapping을 사용하는 방법은 굉장히 다양하며, 여기서는 expression으로 team 필드를 teamId 필드로 바꿨다. */
    @Mapping(target = "teamId", expression = "java(user.getTeam().getId())")
    UserDto userToUserDto(User user);

    /* 2. 다시 Entity로!
     * 다시 Entity로 매핑할 때는 거꾸로 다시 매핑할 필요가 없다.
     * InheritInverseConfiguration을 사용하여 동일한 과정을 역으로 반복할 수 있다. */
    /* 하지만 잘 생각해보자. 현재 입력값은 int형 id이므로, 이는 Entity의 team 필드의 id값에 들어가야한다.
     * 따라서 위와같이 id값에 teamId가 들어가도록 설정해주자
     */
    @Mapping(target = "team.id", expression = "java(userDto.getTeamId())")
    User userDtoToUser(UserDto userDto);
}
