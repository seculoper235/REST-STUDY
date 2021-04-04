package com.example.demo.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
