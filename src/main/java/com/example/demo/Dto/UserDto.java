package com.example.demo.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 이렇게 VO와 Response 객체가 별반 다르지 않을 경우엔, 굳이 DTO 객체를 작성할 필요는 없다.
 * 하지만 일단 공부 목적상 작성해보았다. */
@Getter
@NoArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String description;
    private int teamId;

    @Builder
    public UserDto(int id, String name, String description, int teamId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teamId = teamId;
    }
}
