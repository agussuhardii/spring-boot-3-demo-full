package com.agussuhardi.springdemofull.controller;

import com.agussuhardi.springdemofull.config.GlobalApiResponse;
import com.agussuhardi.springdemofull.service.AuthService;
import com.agussuhardi.springdemofull.vo.LoginVO;
import com.agussuhardi.springdemofull.vo.RegisterVO;
import com.agussuhardi.springdemofull.vo.UpdateUserVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginVO vo) {
        return new GlobalApiResponse<>(authService.login(vo), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getMySelf() {
        return new GlobalApiResponse<>(authService.getMySelf(), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> getMySelf(@RequestBody @Valid UpdateUserVO vo) {
        authService.updateMySelf(vo);
        return new GlobalApiResponse<>(authService.getMySelf(), HttpStatus.CREATED);
    }

}
