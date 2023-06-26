package com.agussuhardi.springdemofull.service.impl;

import com.agussuhardi.springdemofull.dto.CategoryDTO;
import com.agussuhardi.springdemofull.entity.Category;
import com.agussuhardi.springdemofull.repository.CategoryRepository;
import com.agussuhardi.springdemofull.vo.CategoryQueryVO;
import com.agussuhardi.springdemofull.vo.CategoryUpdateVO;
import com.agussuhardi.springdemofull.vo.CategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String save(CategoryVO vO) {
        Category bean = new Category();
        BeanUtils.copyProperties(vO, bean);
        bean = categoryRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

    public void update(String id, CategoryUpdateVO vO) {
        Category bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        categoryRepository.save(bean);
    }

    public CategoryDTO getById(String id) {
        Category original = requireOne(id);
        return toDTO(original);
    }

    public Page<CategoryDTO> query(CategoryQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CategoryDTO toDTO(Category original) {
        CategoryDTO bean = new CategoryDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Category requireOne(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
