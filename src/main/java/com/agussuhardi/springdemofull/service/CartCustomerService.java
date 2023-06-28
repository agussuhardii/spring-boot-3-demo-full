package com.agussuhardi.springdemofull.service;

import com.agussuhardi.springdemofull.dto.CartDTO;
import com.agussuhardi.springdemofull.dto.CreateDTO;
import com.agussuhardi.springdemofull.vo.CartVO;

/**
 * @author agussuhardi
 * @created 28/06/23/06/2023 :19.14
 * @project spring-demo-full
 */
public interface CartCustomerService {
    CreateDTO addItems(CartVO vo);

    CartDTO getByPrincipal();
}
