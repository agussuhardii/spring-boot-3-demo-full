package com.agussuhardi.springdemofull.entity;

import org.springframework.security.core.GrantedAuthority;
/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.16
 * @project spring-demo-full
 */
public enum UserRole implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_SUPER_ADMIN,
    ROLE_CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}