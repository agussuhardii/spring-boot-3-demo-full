package com.agussuhardi.springdemofull.service.impl;

import com.agussuhardi.springdemofull.entity.User;
import com.agussuhardi.springdemofull.entity.UserRole;
import com.agussuhardi.springdemofull.repository.UserRepository;
import com.agussuhardi.springdemofull.service.AuthService;
import com.agussuhardi.springdemofull.service.UserService;
import com.agussuhardi.springdemofull.vo.RegisterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    @Override
    public void register(RegisterVO vo) {
        var user = new User();
        BeanUtils.copyProperties(vo, user, User.Fields.password);
        user.setPassword(new BCryptPasswordEncoder().encode(vo.password()));
        user.setRoles(Collections.singletonList(UserRole.ROLE_CUSTOMER));
        user.setDeleted(false);
        var username = userService.createUserName(vo.fullName());
        user.setUsername(username);
        userRepository.save(user);
    }


}
