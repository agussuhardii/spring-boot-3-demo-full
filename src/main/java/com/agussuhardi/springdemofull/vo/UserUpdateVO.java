package com.agussuhardi.springdemofull.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * @author agus.suhardii@gmail.com
 * @created 14/06/23/06/2023 :21.16
 * @project spring-demo-full
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserUpdateVO extends UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
