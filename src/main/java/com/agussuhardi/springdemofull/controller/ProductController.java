package com.agussuhardi.springdemofull.controller;

import com.agussuhardi.springdemofull.config.GlobalApiResponse;
import com.agussuhardi.springdemofull.service.ProductService;
import com.agussuhardi.springdemofull.vo.ProductQueryVO;
import com.agussuhardi.springdemofull.vo.ProductUpdateQtyVO;
import com.agussuhardi.springdemofull.vo.ProductUpdateVO;
import com.agussuhardi.springdemofull.vo.ProductVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductVO vO) {
        return new GlobalApiResponse<>(productService.save(vO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @NotNull @PathVariable("id") String id) {
        productService.delete(id);
        return new GlobalApiResponse<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @NotNull @PathVariable("id") String id,
                                    @Valid @RequestBody ProductUpdateVO vO) {
        productService.update(id, vO);
        return new GlobalApiResponse<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Valid @NotNull @PathVariable("id") String id) {
        return new GlobalApiResponse<>(productService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> query(@Valid ProductQueryVO vO, Pageable pageable) {
        return new GlobalApiResponse<>(productService.query(vO, pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}/qty")
    public ResponseEntity<?> updateQty(@Valid @NotNull @PathVariable("id") String id,
                                       @Valid @RequestBody ProductUpdateQtyVO vO) {
        productService.updateQty(id, vO);
        return new GlobalApiResponse<>(HttpStatus.OK);
    }

}
