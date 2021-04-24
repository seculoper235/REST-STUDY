package com.example.demo.Mapper;

/* Object Mapping 란?
 * Entity로 데이터베이스와 연동하고, VO로 객체와 연동하며, DTO로 출력을 위한 데이터로 변환한다는 것은 알았다.
 * (VO는 간단한 애플리케이션에선 오히려 더 복잡하므로, VO가 아닌 DTO로 객체와 연동하도록 하겠다)
 * Entity는 JPA로 연동이 되는데, 그럼 DTO는 어떻게 Entity와 연동을 할 수 있을까?
 * 그것을 바로 객체와 매핑시킨다고 해서 Object Mapping 이라고 한다
 */

import com.example.demo.Domain.Team;
import com.example.demo.Dto.TeamDto;

/* Mapper 클래스
 * 말 그대로 Object Mapping을 구현하기 위한 클래스를 말한다.
 * 이 클래스를 작성하는데에는 많은 방법들이 있지만, 그중 잘 알려진 3가지의 방법이 있다.
 * Only JAVA, ModelMapper 라이브러리 이용, MapStruct 라이브러리 이용 이 그것이다.
 * 우선 오직 JAVA 그 자체로만 작성하는 방법을 알아보자.  */
public class TeamMapper {
    // 방법은 간단하다. 그냥 Builder 패턴으로 필요한 값을 초기화 시켜주면 된다
    Team toEntity(TeamDto teamDto) {
        return Team.builder()
                .id(teamDto.getId())
                .name(teamDto.getName())
                .build();
    }

    TeamDto toDto(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .count(team.getUsers().size())
                .build();
    }
}
