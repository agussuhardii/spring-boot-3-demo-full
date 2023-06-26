package com.agussuhardi.springdemofull.controller;

import com.agussuhardi.springdemofull.dto.ProductDTO;
import com.agussuhardi.springdemofull.service.impl.ProductService;
import com.agussuhardi.springdemofull.vo.ProductQueryVO;
import com.agussuhardi.springdemofull.vo.ProductUpdateVO;
import com.agussuhardi.springdemofull.vo.ProductVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public String save(@Valid @RequestBody ProductVO vO) {
        return productService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody ProductUpdateVO vO) {
        productService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return productService.getById(id);
    }

    @GetMapping
    public Page<ProductDTO> query(@Valid ProductQueryVO vO) {
        return productService.query(vO);
    }
}
