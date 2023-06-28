package com.agussuhardi.springdemofull.controller.customer;

import com.agussuhardi.springdemofull.config.GlobalApiResponse;
import com.agussuhardi.springdemofull.service.impl.CartCustomerServiceImpl;
import com.agussuhardi.springdemofull.vo.CartVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("customer/api/v1/carts")
@RequiredArgsConstructor
public class CartCustomerController {


    private final CartCustomerServiceImpl cartService;

    @PostMapping
    public ResponseEntity<?> addItems(@Valid @RequestBody CartVO vO) {
        return new GlobalApiResponse<>(cartService.addItems(vO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getByPrincipal() {
        return new GlobalApiResponse<>(cartService.getByPrincipal(), HttpStatus.OK);
    }

}
