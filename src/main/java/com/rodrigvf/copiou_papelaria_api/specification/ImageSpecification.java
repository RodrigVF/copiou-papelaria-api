package com.rodrigvf.copiou_papelaria_api.specification;

import com.rodrigvf.copiou_papelaria_api.entity.Image;
import com.rodrigvf.copiou_papelaria_api.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ImageSpecification {

    public static Specification<Image> isActive(Boolean active) {
        return (Root<Image> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (active != null) {
                return criteriaBuilder.equal(root.get("isActive"), active);
            }
            return null;
        };
    }

    public static Specification<Image> isBanner(Boolean banner) {
        return (Root<Image> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (banner != null) {
                return criteriaBuilder.equal(root.get("isBanner"), banner);
            }
            return null;
        };
    }

}
