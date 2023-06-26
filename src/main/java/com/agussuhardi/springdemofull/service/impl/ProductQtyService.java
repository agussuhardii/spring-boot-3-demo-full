package com.agussuhardi.springdemofull.service.impl;

import com.agussuhardi.springdemofull.dto.ProductQtyDTO;
import com.agussuhardi.springdemofull.entity.ProductQty;
import com.agussuhardi.springdemofull.repository.ProductQtyRepository;
import com.agussuhardi.springdemofull.vo.ProductQtyQueryVO;
import com.agussuhardi.springdemofull.vo.ProductQtyUpdateVO;
import com.agussuhardi.springdemofull.vo.ProductQtyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductQtyService {

    @Autowired
    private ProductQtyRepository productQtyRepository;

    public String save(ProductQtyVO vO) {
        ProductQty bean = new ProductQty();
        BeanUtils.copyProperties(vO, bean);
        bean = productQtyRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        productQtyRepository.deleteById(id);
    }

    public void update(String id, ProductQtyUpdateVO vO) {
        ProductQty bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        productQtyRepository.save(bean);
    }

    public ProductQtyDTO getById(String id) {
        ProductQty original = requireOne(id);
        return toDTO(original);
    }

    public Page<ProductQtyDTO> query(ProductQtyQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ProductQtyDTO toDTO(ProductQty original) {
        ProductQtyDTO bean = new ProductQtyDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ProductQty requireOne(String id) {
        return productQtyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
