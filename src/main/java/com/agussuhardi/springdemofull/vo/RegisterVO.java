package com.agussuhardi.springdemofull.vo;

import com.agussuhardi.springdemofull.validation.EmailConstraints;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
        @Pattern(regexp = "(?=(.*[0-9]))(?=.*[!@#$%^&*()\\[\\]{}\\-_+=~`|:;\"'<>,./?])(?=.*[a-z])(?=(.*[A-Z]))(?=(.*)).{6,}", message = "Minimal 6 karakter dan terdapat huruf besar kecil, simbol")
        String password,
        String placeBirth,
        LocalDate dateBirth,
        String gender,
        String phoneNumber,
        @NotNull(message = "email can not null")
        @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$")
        @EmailConstraints
        String email,
        String fullAddress
) {
}

