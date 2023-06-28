package com.agussuhardi.springdemofull.service.impl;

import com.agussuhardi.springdemofull.service.AuthService;
import com.agussuhardi.springdemofull.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author agussuhardi
 * @created 28/06/23/06/2023 :20.05
 * @project spring-demo-full
 */
@SpringBootTest
@Slf4j
@Disabled
class AuthServiceImplTest {

    @Autowired
    private AuthService authService;

    @Test
    void login() {
        var res = authService.login(new LoginVO("customer", "password"));
        log.debug(res);
    }
}