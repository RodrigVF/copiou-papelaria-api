package com.rodrigvf.copiou_papelaria_api.repository;

import com.rodrigvf.copiou_papelaria_api.entity.Brand;
import com.rodrigvf.copiou_papelaria_api.entity.Product;
import com.rodrigvf.copiou_papelaria_api.entity.SalesPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SalesPointRepository extends JpaRepository<SalesPoint, Long>, JpaSpecificationExecutor<SalesPoint> {

    List<Product> findByIsActive(Boolean isActive);

}
