package com.example.demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


/* ErrorCode란?
 * 여기선 예외 객체를 생성하고, 응답 객체를 반들어서 반환하는 완전한 커스텀 예외를 만들 것이다.
 * 하지만 응답마저 입맛 대로 바꾸려면 응답으로 내보낼 에러 내용을 작성해야 하는데, 각 예외 객체마다 일일히 작성하긴 귀찮다.
 * 그래서 그런 응답 내용을 한곳에 다 모아서 관리하는 것이 바로 이 ErrorCode enum이다.
 * 보통 HTTP 상태코드와 자체적인 에러 식별코드, 그리고 간단한 설명을 담는다.*/
@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_PARAM(400, "E001", "Invalid Parameter."),
    RESOURCE_NOT_FOUND(404, "E002", "Resource not found."),
    AUTH_FORBIDDEN(401, "E003", "Authorization forbidden.");

    private final int httpcode;
    private final String code;
    private final String description;
}
