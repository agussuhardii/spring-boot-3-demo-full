package com.agussuhardi.springdemofull.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.16
 * @project spring-demo-full
 */
public record UpdateUserVO(
        @NotNull(message = "fullName can not null")
        String fullName,
        String placeBirth,
        LocalDate dateBirth,
        String gender,
        String phoneNumber,
        String fullAddress
) {
}
