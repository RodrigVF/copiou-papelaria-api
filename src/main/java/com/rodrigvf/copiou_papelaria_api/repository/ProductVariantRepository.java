package com.rodrigvf.copiou_papelaria_api.repository;

import com.rodrigvf.copiou_papelaria_api.entity.Product;
import com.rodrigvf.copiou_papelaria_api.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long>, JpaSpecificationExecutor<ProductVariant> {

    List<ProductVariant> findProductVariantByProduct(Product product);

    List<ProductVariant> findByIsActive(Boolean isActive);
}
