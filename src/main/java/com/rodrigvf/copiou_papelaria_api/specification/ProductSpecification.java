package com.rodrigvf.copiou_papelaria_api.specification;

import com.rodrigvf.copiou_papelaria_api.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> isActive(Boolean active) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (active != null) {
                return criteriaBuilder.equal(root.get("isActive"), active);
            }
            return null;
        };
    }

    public static Specification<Product> hasBrand(Long brandId) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (brandId != null) {
                return criteriaBuilder.equal(root.get("brand").get("id"), brandId);
            }
            return null;
        };
    }

}
