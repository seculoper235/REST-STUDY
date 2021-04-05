package com.example.demo.Exception;

import lombok.Getter;
import java.time.LocalDateTime;


/* ExceptionResponse란?
 * 흔히 쓰는 Response 객체 예외 버전이다. 입맛대로 구성해주면 된다. */
@Getter
public class ExceptionResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String message;
    private String code;

    public ExceptionResponse() {
    }

    static public ExceptionResponse create() {
        return new ExceptionResponse();
    }

    public ExceptionResponse code(String code) {
        this.code = code;
        return this;
    }

    public ExceptionResponse status(int status) {
        this.status = status;
        return this;
    }

    public ExceptionResponse message(String message) {
        this.message = message;
        return this;
    }
}
