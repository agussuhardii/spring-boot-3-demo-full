package com.agussuhardi.springdemofull.vo;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.16
 * @project spring-demo-full
 */
public record RegisterVO(
        @NotNull(message = "fullName can not null")
        String fullName,
        @NotNull(message = "password can not null")
        String password,
        String placeBirth,
        LocalDate dateBirth,
        String gender,
        String phoneNumber,
        String email,
        String fullAddress
) {
}
