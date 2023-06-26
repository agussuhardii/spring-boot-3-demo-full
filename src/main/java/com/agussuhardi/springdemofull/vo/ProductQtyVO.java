package com.agussuhardi.springdemofull.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class ProductQtyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private String id;

    @NotNull(message = "productId can not null")
    private String productId;

    @NotNull(message = "qty can not null")
    private BigDecimal qty;

    @NotNull(message = "adjust can not null")
    private BigDecimal adjust;

}
