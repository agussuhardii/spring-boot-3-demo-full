package com.agussuhardi.springdemofull.service;

import com.agussuhardi.springdemofull.dto.UsersDTO;
import com.agussuhardi.springdemofull.vo.LoginVO;
import com.agussuhardi.springdemofull.vo.RegisterVO;
import com.agussuhardi.springdemofull.vo.UpdateUserVO;

/**
 * @author agus.suhardii@gmail.com
 * @created 15/06/23/06/2023 :06.36
 * @project spring-demo-full
 */
public interface AuthService {
    void register(RegisterVO vo);

    String login(LoginVO vo);

    UsersDTO getMySelf();

    void updateMySelf(UpdateUserVO vo);
}
