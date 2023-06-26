package com.agussuhardi.springdemofull.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductQtyUpdateVO extends ProductQtyVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
