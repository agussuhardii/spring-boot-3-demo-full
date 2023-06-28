package com.agussuhardi.springdemofull.vo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


public record CartVO(
        List<CartItemVO> items

) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


}
