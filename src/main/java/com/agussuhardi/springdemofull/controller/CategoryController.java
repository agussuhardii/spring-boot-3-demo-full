package com.agussuhardi.springdemofull.controller;

import com.agussuhardi.springdemofull.config.GlobalApiResponse;
import com.agussuhardi.springdemofull.service.CategoryService;
import com.agussuhardi.springdemofull.vo.CategoryQueryVO;
import com.agussuhardi.springdemofull.vo.CategoryVO;
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
@RequestMapping("api/v1/products/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CategoryVO vO) {
        return new GlobalApiResponse<>(categoryService.add(vO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @NotNull @PathVariable("id") String id) {
        categoryService.delete(id);
        return new GlobalApiResponse<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @NotNull @PathVariable("id") String id,
                                    @Valid @RequestBody CategoryVO vO) {
        categoryService.update(id, vO);
        return new GlobalApiResponse<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Valid @NotNull @PathVariable("id") String id) {
        return new GlobalApiResponse<>(categoryService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> query(@Valid CategoryQueryVO vO, Pageable pageable) {
        return new GlobalApiResponse<>(categoryService.query(vO, pageable), HttpStatus.OK);
    }
}
