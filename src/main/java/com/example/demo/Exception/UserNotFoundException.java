package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// 대부분의 예외들은 비슷하므로, CommonException을 만들어놓고, 원하는 예외마다 상속받아서 간단히 생성자로 처리할 수 있게끔 하였다.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends CommonException {
    private static final Long serialID = 2L;

    public UserNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND);
    }
}
