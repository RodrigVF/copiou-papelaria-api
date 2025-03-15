package com.rodrigvf.copiou_papelaria_api.repository;

import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import com.rodrigvf.copiou_papelaria_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findProductByBrand(Brand brand);

    List<Product> findByIsActive(Boolean isActive);

}
