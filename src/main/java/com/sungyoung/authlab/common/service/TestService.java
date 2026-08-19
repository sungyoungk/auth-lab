package com.sungyoung.authlab.common.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String sayHello() {
        return "Hello";
    }
}

/**
 * @Service가 하는 일:
 *
 * "이 클래스를 Spring이 관리하는 객체(Bean)로 등록해줘"
 * 이게 없으면 Spring은 이 클래스의 존재를 모름
 * @Component와 같은 역할인데, "서비스 계층"임을 표현
 */