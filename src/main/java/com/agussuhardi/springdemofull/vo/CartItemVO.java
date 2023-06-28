package com.agussuhardi.springdemofull.vo;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;


public record CartItemVO(
        @NotNull(message = "productId can not null")
        String productId,
        @NotNull(message = "qty can not null")
        Long qty
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


}
