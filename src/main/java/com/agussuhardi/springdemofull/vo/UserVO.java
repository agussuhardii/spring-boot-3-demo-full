package com.agussuhardi.springdemofull.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.16
 * @project spring-demo-full
 */
@Data
public class UserVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private String id;

    @NotNull(message = "fullName can not null")
    private String fullName;

    private String placeBirth;

    private LocalDate dateBirth;

    private String gender;

    private String phoneNumber;

    private String email;

    private String fullAddress;

    @NotNull(message = "deleted can not null")
    private Boolean deleted;

}
