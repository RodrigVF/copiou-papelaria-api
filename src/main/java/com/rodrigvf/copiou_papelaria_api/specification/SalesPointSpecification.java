package com.rodrigvf.copiou_papelaria_api.specification;

import com.rodrigvf.copiou_papelaria_api.entity.SalesPoint;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class SalesPointSpecification {

    public static Specification<SalesPoint> isActive(Boolean active) {
        return (Root<SalesPoint> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (active != null) {
                return criteriaBuilder.equal(root.get("isActive"), active);
            }
            return null;
        };
    }

}
