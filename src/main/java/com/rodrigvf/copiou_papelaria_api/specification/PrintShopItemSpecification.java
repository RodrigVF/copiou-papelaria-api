package com.rodrigvf.copiou_papelaria_api.specification;

import com.rodrigvf.copiou_papelaria_api.entity.PrintShopItem;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class PrintShopItemSpecification {

    public static Specification<PrintShopItem> isActive(Boolean active) {
        return (Root<PrintShopItem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (active != null) {
                return criteriaBuilder.equal(root.get("isActive"), active);
            }
            return null;
        };
    }

}
