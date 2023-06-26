package com.agussuhardi.springdemofull.controller;

import com.agussuhardi.springdemofull.dto.ProductQtyDTO;
import com.agussuhardi.springdemofull.service.impl.ProductQtyService;
import com.agussuhardi.springdemofull.vo.ProductQtyQueryVO;
import com.agussuhardi.springdemofull.vo.ProductQtyUpdateVO;
import com.agussuhardi.springdemofull.vo.ProductQtyVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/productQty")
public class ProductQtyController {

    @Autowired
    private ProductQtyService productQtyService;

    @PostMapping
    public String save(@Valid @RequestBody ProductQtyVO vO) {
        return productQtyService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        productQtyService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody ProductQtyUpdateVO vO) {
        productQtyService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ProductQtyDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return productQtyService.getById(id);
    }

    @GetMapping
    public Page<ProductQtyDTO> query(@Valid ProductQtyQueryVO vO) {
        return productQtyService.query(vO);
    }
}
