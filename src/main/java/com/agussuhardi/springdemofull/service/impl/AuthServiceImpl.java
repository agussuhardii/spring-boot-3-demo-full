package com.agussuhardi.springdemofull.service.impl;

import com.agussuhardi.springdemofull.config.exception.CustomException;
import com.agussuhardi.springdemofull.config.security.JwtService;
import com.agussuhardi.springdemofull.config.security.facade.UserInfo;
import com.agussuhardi.springdemofull.dto.UsersDTO;
import com.agussuhardi.springdemofull.entity.User;
import com.agussuhardi.springdemofull.entity.UserRole;
import com.agussuhardi.springdemofull.repository.UserRepository;
import com.agussuhardi.springdemofull.service.AuthService;
import com.agussuhardi.springdemofull.service.UserService;
import com.agussuhardi.springdemofull.vo.LoginVO;
import com.agussuhardi.springdemofull.vo.RegisterVO;
import com.agussuhardi.springdemofull.vo.UpdateUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.11
 * @project spring-demo-full
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(RegisterVO vo) {
        var user = new User();
        BeanUtils.copyProperties(vo, user, User.Fields.password);
        user.setPassword(new BCryptPasswordEncoder().encode(vo.password()));
        user.setRoles(Collections.singletonList(UserRole.ROLE_CUSTOMER));
        var username = userService.createUserName(vo.fullName());
        user.setUsername(username);
        userRepository.save(user);
    }

    @Override
    public String login(LoginVO vo) {
        var user = userRepository.login(vo.username())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "Username or password not match"));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, vo.password(), user.getAuthorities()));
        return jwtService.create(user);
    }

    @Override
    public UsersDTO getMySelf() {
        return userService.getById(UserInfo.getPrincipal().getId());
    }


    @Override
    public void updateMySelf(UpdateUserVO vo) {
        var user = userRepository.findById(UserInfo.getPrincipal().getId()).orElseThrow();
        BeanUtils.copyProperties(vo, user);
        userRepository.save(user);
    }
}
