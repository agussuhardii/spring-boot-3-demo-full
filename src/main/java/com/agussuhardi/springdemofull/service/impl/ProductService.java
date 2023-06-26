package com.agussuhardi.springdemofull.service.impl;

import com.agussuhardi.springdemofull.dto.ProductDTO;
import com.agussuhardi.springdemofull.entity.Product;
import com.agussuhardi.springdemofull.repository.ProductRepository;
import com.agussuhardi.springdemofull.vo.ProductQueryVO;
import com.agussuhardi.springdemofull.vo.ProductUpdateVO;
import com.agussuhardi.springdemofull.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String save(ProductVO vO) {
        Product bean = new Product();
        BeanUtils.copyProperties(vO, bean);
        bean = productRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }

    public void update(String id, ProductUpdateVO vO) {
        Product bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        productRepository.save(bean);
    }

    public ProductDTO getById(String id) {
        Product original = requireOne(id);
        return toDTO(original);
    }

    public Page<ProductDTO> query(ProductQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ProductDTO toDTO(Product original) {
        ProductDTO bean = new ProductDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Product requireOne(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
