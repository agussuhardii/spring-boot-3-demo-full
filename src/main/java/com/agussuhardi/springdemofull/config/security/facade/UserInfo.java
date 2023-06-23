package com.agussuhardi.springdemofull.config.security.facade;

import com.agussuhardi.springdemofull.config.exception.CustomException;
import com.agussuhardi.springdemofull.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public final class UserInfo {

    public static User getPrincipal() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null)
            throw new CustomException(HttpStatus.FORBIDDEN, "aaaaaaaaaaaaaa");
        return (User) principal;
    }
}
