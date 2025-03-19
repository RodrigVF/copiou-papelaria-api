package com.rodrigvf.copiou_papelaria_api.repository;

import com.rodrigvf.copiou_papelaria_api.entity.PrintShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PrintShopItemRepository extends JpaRepository<PrintShopItem, Long>, JpaSpecificationExecutor<PrintShopItem> {

    List<PrintShopItem> findByIsActive(Boolean isActive);

}
