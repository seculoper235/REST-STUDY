package com.example.demo.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// 작성해둔 TeamDto를 리스트 형태로 반환하기 위한 Response 객체
@Getter
@Setter
public class TeamListResponse {
    private List<TeamDto> teamList;

    @Builder
    public TeamListResponse(TeamDto teamDto) {

    }
}
