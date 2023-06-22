package com.agussuhardi.springdemofull.vo;

import jakarta.validation.constraints.NotNull;

/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.16
 * @project spring-demo-full
 */
public record LoginVO(
        @NotNull(message = "fullName can not null")
        String username,
        @NotNull(message = "password can not null")
        String password
) {
}
