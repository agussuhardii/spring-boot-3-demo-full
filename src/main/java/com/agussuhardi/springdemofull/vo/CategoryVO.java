package com.agussuhardi.springdemofull.vo;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author agussuhardi
 * @created 28/06/23/06/2023 :13.21
 * @project spring-demo-full
 */

public record CategoryVO(
        @NotNull(message = "name can not null")
        String name,
        String text,
        String icon

) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


}
