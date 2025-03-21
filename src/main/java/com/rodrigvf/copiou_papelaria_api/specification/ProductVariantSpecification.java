package com.rodrigvf.copiou_papelaria_api.specification;

import com.rodrigvf.copiou_papelaria_api.entity.ProductVariant;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductVariantSpecification {

    public static Specification<ProductVariant> isActive(Boolean active) {
        return (Root<ProductVariant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (active != null) {
                return criteriaBuilder.equal(root.get("isActive"), active);
            }
            return null;
        };
    }

    public static Specification<ProductVariant> hasProduct(Long productId) {
        return (Root<ProductVariant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (productId != null) {
                return criteriaBuilder.equal(root.get("product").get("id"), productId);
            }
            return null;
        };
    }

}
