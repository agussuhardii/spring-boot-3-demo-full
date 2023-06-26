package com.agussuhardi.springdemofull.repository;

import com.agussuhardi.springdemofull.entity.ProductQty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductQtyRepository extends JpaRepository<ProductQty, String>, JpaSpecificationExecutor<ProductQty> {

}