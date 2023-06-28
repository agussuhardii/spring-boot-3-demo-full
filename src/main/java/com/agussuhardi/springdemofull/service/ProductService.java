package com.agussuhardi.springdemofull.service;

import com.agussuhardi.springdemofull.dto.CreateDTO;
import com.agussuhardi.springdemofull.dto.ProductDTO;
import com.agussuhardi.springdemofull.entity.Product;
import com.agussuhardi.springdemofull.vo.ProductQueryVO;
import com.agussuhardi.springdemofull.vo.ProductUpdateQtyVO;
import com.agussuhardi.springdemofull.vo.ProductUpdateVO;
import com.agussuhardi.springdemofull.vo.ProductVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    CreateDTO save(ProductVO vO);

    void delete(String id);

    void update(String id, ProductUpdateVO vO);

    void updateQty(String id, ProductUpdateQtyVO vo);

    ProductDTO getById(String id);

    Page<ProductDTO> query(ProductQueryVO vO, Pageable pageable);

    Product requireOne(String id);
}
