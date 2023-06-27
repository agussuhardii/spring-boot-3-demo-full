package com.agussuhardi.springdemofull.service;

import com.agussuhardi.springdemofull.dto.CategoryDTO;
import com.agussuhardi.springdemofull.dto.CreateDTO;
import com.agussuhardi.springdemofull.vo.CategoryQueryVO;
import com.agussuhardi.springdemofull.vo.CategoryUpdateVO;
import com.agussuhardi.springdemofull.vo.CategoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CreateDTO save(CategoryVO vO);

    void delete(String id);

    void update(String id, CategoryUpdateVO vO);

    CategoryDTO getById(String id);

    Page<CategoryDTO> query(CategoryQueryVO vO, Pageable pageable);
}
