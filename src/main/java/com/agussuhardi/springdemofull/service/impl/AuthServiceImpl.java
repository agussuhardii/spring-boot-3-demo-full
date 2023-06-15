package com.agussuhardi.springdemofull.service.impl;

import com.agussuhardi.springdemofull.entity.User;
import com.agussuhardi.springdemofull.repository.UserRepository;
import com.agussuhardi.springdemofull.vo.RegisterVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.11
 * @project spring-demo-full
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements com.agussuhardi.springdemofull.service.AuthService {
    private final UserRepository userRepository;

    @Override
    public void register(RegisterVO vo){
        var user = new User();
        BeanUtils.copyProperties(vo, user, User.Fields.password);


    }
}
