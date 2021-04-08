package com.example.demo.Handler;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.Exception.ExceptionResponse;
import com.example.demo.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


/* @ControllerAdvice란?
 * Controller의 역할과 비슷하다. 주로 예외를 처리하는 역할로 쓰인다.
 * 모든 예외를 받아서 처리하기 때문에, 빈번하게 쓰이는 공통 예외 처리를 할 때 쓰인다.
 * 또한 @RestController를 사용하여, JSON 형식으로 원하는 응답을 내보낼 수 있다. 해당 응답으론 작성한 ExceptionResonse를 사용한다. */
@ControllerAdvice
@RestController
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserException(UserNotFoundException e) {
        ErrorCode errorCode = e.getErrorCode();

        ExceptionResponse response = ExceptionResponse.create()
                .status(errorCode.getHttpcode())
                .message(e.toString())
                .code(errorCode.getCode());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
