package com.agussuhardi.springdemofull.controller;

import com.agussuhardi.springdemofull.config.GlobalApiResponse;
import com.agussuhardi.springdemofull.service.AuthService;
import com.agussuhardi.springdemofull.vo.RegisterVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author agus.suhardii@gmail.com
 * @created 15/06/23/06/2023 :06.33
 * @project spring-demo-full
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/auth")
@Validated
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterVO vo) {
        authService.register(vo);
        return new GlobalApiResponse<>(HttpStatus.CREATED);
    }

}
