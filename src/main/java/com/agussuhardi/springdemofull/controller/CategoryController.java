package com.agussuhardi.springdemofull.controller;

import com.agussuhardi.springdemofull.dto.CategoryDTO;
import com.agussuhardi.springdemofull.service.impl.CategoryService;
import com.agussuhardi.springdemofull.vo.CategoryQueryVO;
import com.agussuhardi.springdemofull.vo.CategoryUpdateVO;
import com.agussuhardi.springdemofull.vo.CategoryVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public String save(@Valid @RequestBody CategoryVO vO) {
        return categoryService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody CategoryUpdateVO vO) {
        categoryService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return categoryService.getById(id);
    }

    @GetMapping
    public Page<CategoryDTO> query(@Valid CategoryQueryVO vO) {
        return categoryService.query(vO);
    }
}
