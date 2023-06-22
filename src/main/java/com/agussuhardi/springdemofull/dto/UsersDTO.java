package com.agussuhardi.springdemofull.dto;


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
public class UsersDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;

    private String fullName;

    private String placeBirth;

    private LocalDate dateBirth;

    private String gender;

    private String phoneNumber;

    private String email;

    private String fullAddress;

    private Boolean deleted;

}
