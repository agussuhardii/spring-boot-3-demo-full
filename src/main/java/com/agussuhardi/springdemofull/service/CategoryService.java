package com.agussuhardi.springdemofull.service;

import com.agussuhardi.springdemofull.dto.CategoryDTO;
import com.agussuhardi.springdemofull.dto.CreateDTO;
import com.agussuhardi.springdemofull.vo.CategoryQueryVO;
import com.agussuhardi.springdemofull.vo.CategoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CreateDTO add(CategoryVO vO);

    void delete(String id);

    void update(String id, CategoryVO vO);

    CategoryDTO getById(String id);

    Page<CategoryDTO> query(CategoryQueryVO vO, Pageable pageable);
}
