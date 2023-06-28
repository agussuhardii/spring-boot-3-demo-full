package com.agussuhardi.springdemofull.vo;

import java.io.Serial;
import java.io.Serializable;


public record ProductUpdateQtyVO(
        Long qty) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


}
